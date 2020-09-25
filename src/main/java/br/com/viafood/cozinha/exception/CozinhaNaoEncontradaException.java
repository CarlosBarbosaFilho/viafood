/**
 * 
 */
package br.com.viafood.cozinha.exception;

import br.com.viafood.exceptions.exception.EntidadeNaoEncontradaException;
import br.com.viafood.utils.constantes.Constantes;

/**
 * @author cbgomes
 *
 */
public class CozinhaNaoEncontradaException extends EntidadeNaoEncontradaException {
	private static final long serialVersionUID = 1L;

	public CozinhaNaoEncontradaException(String msg) {
		super(msg);
	}
	
	public CozinhaNaoEncontradaException(Long idCozinha) {
		this(String.format(Constantes.COZINHA_NAO_ENCONTRADA, idCozinha));
	}
	
	public CozinhaNaoEncontradaException(Object nome) {
		this(String.format(Constantes.COZINHA_NAO_ENCONTRADA, nome));
	}
}
