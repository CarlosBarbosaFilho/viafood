/**
 * 
 */
package br.com.viafood.grupo.exception;

import br.com.viafood.exceptions.exception.EntidadeNaoEncontradaException;
import br.com.viafood.utils.constantes.Constantes;

/**
 * @author david
 *
 */
public class GrupoNaoEncontradoException extends EntidadeNaoEncontradaException {
	private static final long serialVersionUID = 1L;

	public GrupoNaoEncontradoException(String msg) {
		super(msg);
	}
	
	public GrupoNaoEncontradoException(Long id) {
		this(String.format(Constantes.GRUPO_NAO_ENCONTRADO, id));
	}
}
