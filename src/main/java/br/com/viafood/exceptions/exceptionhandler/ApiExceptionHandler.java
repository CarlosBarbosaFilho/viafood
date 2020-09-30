/**
 * 
 */
package br.com.viafood.exceptions.exceptionhandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;

import br.com.viafood.exceptions.exception.BusinessException;
import br.com.viafood.exceptions.exception.EntidadeComDependencia;
import br.com.viafood.exceptions.exception.EntidadeNaoEncontradaException;
import br.com.viafood.utils.constantes.Constantes;

/**
 * @author cbgomes
 *
 */
@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Autowired
	private MessageSource messageSource;

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Throwable rootCause = ExceptionUtils.getRootCause(ex);

		if (rootCause instanceof InvalidFormatException) {
			return handleInvalidFormatException((InvalidFormatException) rootCause, headers, status, request);

		} else if (rootCause instanceof UnrecognizedPropertyException) {
			return handleIgnoredPropertyException((UnrecognizedPropertyException) rootCause, headers, status, request);

		}

		ErrorApiType typeErrorApi = ErrorApiType.ERROR_REQUEST_CLIENT;
		String details = Constantes.VERIFIQUE_SINTAXE_DA_REQUISICAO;

		ApiErrorException apiErrorException = createApiErrorBuilder(status, typeErrorApi, details)
				.userMessage("Erro no corpo da requisição, verifique sua estrutura e envie novamente")
				.build();
		return handleExceptionInternal(ex, apiErrorException, new HttpHeaders(), status, request);
	}

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		if (ex instanceof MethodArgumentTypeMismatchException) {
			return handleMethodArgumentTypeMismatch((MethodArgumentTypeMismatchException) ex, headers, status, request);
		}
		return super.handleTypeMismatch(ex, headers, status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		ErrorApiType typeErrorApi = ErrorApiType.ERROR_DADOS_INVALIDOS;
		String details = " O campo  não foi informado na requisição";
		
		
		
		List<ApiErrorException.Object> objects = ex.getBindingResult().getAllErrors().stream()
				.map(objectError -> { 
					String message = messageSource.getMessage(objectError, LocaleContextHolder.getLocale());
					String name = objectError.getObjectName();
					
					if(objectError instanceof FieldError) {
						name = ((FieldError) objectError).getField();
					}
					
					return  ApiErrorException.Object.
						builder()
						.nome(name)
						.useMessage(message)
						.build();
				})
				.collect(Collectors.toList());
		
		ApiErrorException apiErrorException = createApiErrorBuilder(status, typeErrorApi, details)
				.userMessage("Dados obrigatórios não informados")
				.objects(objects)
				.build();
		return handleExceptionInternal(ex, apiErrorException, new HttpHeaders(), status, request);
	}
	

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> exception(Exception ex, WebRequest request) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		ErrorApiType typeErrorApi = ErrorApiType.ERROR_SISTEMA;
		String details = Constantes.OCORREU_ERRO_INTERNO_NO_SISTEMA;

		ApiErrorException apiErrorException = createApiErrorBuilder(status, typeErrorApi, details).build();
		return handleExceptionInternal(ex, apiErrorException, new HttpHeaders(), status, request);
	}

	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<?> exceptionEstadoNaoEncontrado(EntidadeNaoEncontradaException ex, WebRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		ErrorApiType typeErrorApi = ErrorApiType.ERROR_TYPE_ENTIDADE_NAO_ENCONTRADA;
		String details = ex.getMessage();

		ApiErrorException apiErrorException = createApiErrorBuilder(status, typeErrorApi, details).build();
		return handleExceptionInternal(ex, apiErrorException, new HttpHeaders(), status, request);
	}

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<?> exceptionBusiness(BusinessException ex, WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ErrorApiType typeErrorApi = ErrorApiType.ERROR_TYPE_BUSINESS_EXCEPTION;
		String details = ex.getMessage();

		ApiErrorException apiErrorException = createApiErrorBuilder(status, typeErrorApi, details)
				.timestamp(LocalDateTime.now())
				.userMessage(Constantes.OCORREU_ERRO_INTERNO_NO_SISTEMA)
				.build();
		return handleExceptionInternal(ex, apiErrorException, new HttpHeaders(), status, request);
	}

	@ExceptionHandler(EntidadeComDependencia.class)
	public ResponseEntity<?> exceptionEntidadeEmUso(BusinessException ex, WebRequest request) {
		HttpStatus status = HttpStatus.CONFLICT;
		ErrorApiType typeErrorApi = ErrorApiType.ERROR_TYPE_ENTIDADE_EM_USO;
		String details = ex.getMessage();

		ApiErrorException apiErrorException = createApiErrorBuilder(status, typeErrorApi, details).build();
		return handleExceptionInternal(ex, apiErrorException, new HttpHeaders(), status, request);
	}

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		ErrorApiType typeErrorApi = ErrorApiType.ERROR_TYPE_ENTIDADE_NAO_ENCONTRADA;
		String detail = String.format(Constantes.RECURSO_ACESSADO_NAO_ENCONTRADO_OU_NAO_EXITE, ex.getRequestURL());

		ApiErrorException apiErrorException = createApiErrorBuilder(status, typeErrorApi, detail).build();
		return handleExceptionInternal(ex, apiErrorException, new HttpHeaders(), status, request);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		if (body == null) {
			body = ApiErrorException.builder()
					.title(status.getReasonPhrase())
					.status(status.value())
					.timestamp(LocalDateTime.now())
					.userMessage(Constantes.OCORREU_ERRO_INTERNO_NO_SISTEMA)
					.build();
		} else if (body instanceof String) {
			body = ApiErrorException.builder()
					.title((String) body)
					.status(status.value())
					.timestamp(LocalDateTime.now())
					.userMessage(Constantes.OCORREU_ERRO_INTERNO_NO_SISTEMA)
					.build();
		}
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}

	private ApiErrorException.ApiErrorExceptionBuilder createApiErrorBuilder(HttpStatus status, ErrorApiType typeError,
			String details) {

		return ApiErrorException.builder()
				.status(status.value())
				.title(typeError.getUri())
				.type(typeError.getTitle())
				.details(details)
				.timestamp(LocalDateTime.now());
	}

	private ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		ErrorApiType typeErrorApi = ErrorApiType.ERROR_URL_PARAM_EXCEPTION;

		String detail = String.format(
				"O parâmetro de URL '%s' recebeu o valor '%s', "
						+ "que é de um tipo inválido. Corrija e informe um valor compatível com o tipo %s.",
				ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName());

		ApiErrorException errorApiType = createApiErrorBuilder(status, typeErrorApi, detail).build();

		return handleExceptionInternal(ex, errorApiType, headers, status, request);
	}

	/**
	 * Retorna uma menasgem para o consumidor sobre envio de attr inválidos
	 * 
	 * @param ex
	 * @param headers
	 * @param status
	 * @param request
	 * @return ResponseEntity<Object> handleIgnoredPropertyException
	 */
	private ResponseEntity<Object> handleIgnoredPropertyException(UnrecognizedPropertyException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		String path = ex.getPath().stream().map(ref -> ref.getFieldName()).collect(Collectors.joining("."));

		ErrorApiType typeErrorApi = ErrorApiType.ERROR_REQUEST_CLIENT;
		String details = String
				.format("A propriedade '%s' não pertence a entidade, remova e envie a requisição novamente ", path);

		ApiErrorException apiExceptionError = createApiErrorBuilder(status, typeErrorApi, details)
				.userMessage("A entidade não possui o atributos " + path + " passado na solicitação")
				.build();

		return handleExceptionInternal(ex, apiExceptionError, headers, status, request);
	}

	/**
	 * Formato de url inválido
	 * 
	 * @param ex
	 * @param headers
	 * @param status
	 * @param request
	 * @return ResponseEntity<Object> handleInvalidFormatException
	 */
	private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		String path = ex.getPath().stream().map(ref -> ref.getFieldName()).collect(Collectors.joining("."));

		ErrorApiType typeErrorApi = ErrorApiType.ERROR_REQUEST_CLIENT;
		String details = String.format(
				"a propriedade '%s' não é compatível com o campo '%s' informado,"
						+ " o valor informado deve ser do tipo é %s",
				path, ex.getValue(), ex.getTargetType().getSimpleName());

		ApiErrorException apiExceptionError = createApiErrorBuilder(status, typeErrorApi, details).build();

		return handleExceptionInternal(ex, apiExceptionError, headers, status, request);
	}

}
