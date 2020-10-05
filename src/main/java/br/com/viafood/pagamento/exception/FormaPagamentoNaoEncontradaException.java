/**
 * 
 */
package br.com.viafood.pagamento.exception;

import br.com.viafood.exceptions.exception.EntidadeNaoEncontradaException;
import br.com.viafood.utils.constantes.Constantes;

/**
 * @author cbgomes
 *
 */
public class FormaPagamentoNaoEncontradaException extends EntidadeNaoEncontradaException {
	private static final long serialVersionUID = 1L;

	public FormaPagamentoNaoEncontradaException(String msg) {
		super(msg);
	}
	
	public FormaPagamentoNaoEncontradaException(Long idFormaPagamento) {
		this(String.format(Constantes.COZINHA_NAO_ENCONTRADA, idFormaPagamento));
	}
	
	public FormaPagamentoNaoEncontradaException(Object nome) {
		this(String.format(Constantes.COZINHA_NAO_ENCONTRADA, nome));
	}
}
