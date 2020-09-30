/**
 * 
 */
package br.com.viafood.exceptions.exception;

import org.springframework.validation.BindingResult;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author david
 *
 */
@AllArgsConstructor
@Getter
public class ValidationException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	private BindingResult bindingResult;
	
	
}
