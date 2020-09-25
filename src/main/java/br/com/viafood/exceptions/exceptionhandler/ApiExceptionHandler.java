/**
 * 
 */
package br.com.viafood.exceptions.exceptionhandler;

import java.util.stream.Collectors;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
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

/**
 * @author cbgomes
 *
 */
@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

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
		String details = "Verifique a sintaxe do corpo da requisição";

		ApiErrorException apiErrorException = createApiErrorBuilder(status, typeErrorApi, details).build();
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

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> exception(Exception ex, WebRequest request) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		ErrorApiType typeErrorApi = ErrorApiType.ERROR_SISTEMA;
		String details = "Ocorreu um erro interno no sistema. Tente novamente mais tarde! Se o problema persistir, entre em contato com a administração ";

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

		ApiErrorException apiErrorException = createApiErrorBuilder(status, typeErrorApi, details).build();
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
		String detail = String.format("O recurso %s que você tentou acessar não existe", ex.getRequestURL());

		ApiErrorException apiErrorException = createApiErrorBuilder(status, typeErrorApi, detail).build();
		return handleExceptionInternal(ex, apiErrorException, new HttpHeaders(), status, request);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		if (body == null) {
			body = ApiErrorException.builder().title(status.getReasonPhrase()).status(status.value()).build();
		} else if (body instanceof String) {

			body = ApiErrorException.builder().title((String) body).status(status.value()).build();
		}

		return super.handleExceptionInternal(ex, body, headers, status, request);
	}

	private ApiErrorException.ApiErrorExceptionBuilder createApiErrorBuilder(HttpStatus status, ErrorApiType typeError,
			String details) {

		return ApiErrorException.builder().status(status.value()).title(typeError.getUri()).type(typeError.getTitle())
				.details(details);
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

		ApiErrorException apiExceptionError = createApiErrorBuilder(status, typeErrorApi, details).build();

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
