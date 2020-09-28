/**exceptionBusiness
 * 
 */
package br.com.viafood.exceptions.exceptionhandler;

import lombok.Getter;

/**
 * @author cbgomes
 *
 */
@Getter
public enum ErrorApiType {
	
	ERROR_DADOS_INVALIDOS("/error-dados-invalidos","Dados inválidos"),
	ERROR_SISTEMA("/error-sistema"," Erro interno do sistema"),
	ERROR_URL_PARAM_EXCEPTION ("/parametro-invalido-na-requisicao", "Verifique o parametro enviado na url de acesso"),
	ERROR_REQUEST_CLIENT ("/corpo-requisicao-invalido", "Erro no corpo da requisição, valide o JSON enviado"),
	ERROR_TYPE_ENTIDADE_NAO_ENCONTRADA ("/entidade-nao-encontrada", "Entidade não localizada ou não existe"),
	ERROR_TYPE_ENTIDADE_EM_USO("/entidade-em-uso", "Entidade possui depenências vinculadas, não pode ser removida"),
	ERROR_TYPE_BUSINESS_EXCEPTION ("/entidade-nao-existe", "Entidade vinculada não existe");
	
	private String title;
	private String uri;
	
	private ErrorApiType(String path, String title) {
		this.uri = "https://viafood.com.br" + path;
		this.title = title;
	}
}
