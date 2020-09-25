/**
 * 
 */
package br.com.viafood.exceptions.exception;

/**
 * @author cbgomes
 *
 */
public abstract class EntidadeNaoEncontradaException extends BusinessException {
	private static final long serialVersionUID = 1L;

	public EntidadeNaoEncontradaException(String msg) {
		super(msg);
	}
}
