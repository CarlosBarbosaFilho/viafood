/**
 * 
 */
package br.com.viafood.usuario.exception;

import br.com.viafood.exceptions.exception.EntidadeNaoEncontradaException;
import br.com.viafood.utils.constantes.Constantes;

/**
 * @author david
 *
 */
public class UsuarioNaoEncontradoException extends EntidadeNaoEncontradaException {
	private static final long serialVersionUID = 1L;

	public UsuarioNaoEncontradoException(String msg) {
		super(msg);
	}

	public UsuarioNaoEncontradoException(Long idUsuario) {
		this(String.format(Constantes.USUARIO_NAO_ENCONTRADO, idUsuario));
	}
}
