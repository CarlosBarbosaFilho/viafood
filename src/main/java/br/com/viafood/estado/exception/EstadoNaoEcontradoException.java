/**
 * 
 */
package br.com.viafood.estado.exception;

import br.com.viafood.exceptions.exception.EntidadeNaoEncontradaException;
import br.com.viafood.utils.constantes.Constantes;

/**
 * @author cbgomes
 *
 */
public class EstadoNaoEcontradoException extends EntidadeNaoEncontradaException {
	private static final long serialVersionUID = 1L;

	public EstadoNaoEcontradoException(String msg) {
		super(msg);
	}

	public EstadoNaoEcontradoException(Long idEstado) {
		this(String.format(Constantes.ESTADO_NAO_ECONTRADO, idEstado));
	}

}
