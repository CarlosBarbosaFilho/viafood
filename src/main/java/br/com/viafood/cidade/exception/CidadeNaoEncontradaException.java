/**
 * 
 */
package br.com.viafood.cidade.exception;

import br.com.viafood.exceptions.exception.EntidadeNaoEncontradaException;
import br.com.viafood.utils.constantes.Constantes;

/**
 * @author cbgomes
 *
 */
public class CidadeNaoEncontradaException extends EntidadeNaoEncontradaException {
	private static final long serialVersionUID = 1L;

	public CidadeNaoEncontradaException(String msg) {
		super(msg);
	}
	
	public CidadeNaoEncontradaException(Long id) {
		this(String.format(Constantes.CIDADE_NAO_ENCONTRADA, id));
	}
}
