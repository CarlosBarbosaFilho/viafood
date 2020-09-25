/**
 * 
 */
package br.com.viafood.exceptions.exception;

/**
 * @author cbgomes
 *
 */
public class BusinessException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public BusinessException(String msg) {
		super(msg);
	}

	public BusinessException(String msg, Throwable causa) {
		super(msg, causa);
	}
	
}
