/**
 * 
 */
package br.com.viafood.cozinha.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author cbgomes
 *
 */
@ResponseStatus(value = HttpStatus.CONFLICT)
public class EntidadeComDependencia extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public EntidadeComDependencia(String msg) {
		super(msg);
	}
}
