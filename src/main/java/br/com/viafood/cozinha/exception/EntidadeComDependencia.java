/**
 * 
 */
package br.com.viafood.cozinha.exception;

/**
 * @author cbgomes
 *
 */
public class EntidadeComDependencia extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public EntidadeComDependencia(String msg) {
		super(msg);
	}
}
