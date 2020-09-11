/**
 * 
 */
package br.com.viafood.cozinha.exception;

/**
 * @author cbgomes
 *
 */
public class EntidadeNaoEncontrada extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public EntidadeNaoEncontrada(String msg) {
		super(msg);
	}
}
