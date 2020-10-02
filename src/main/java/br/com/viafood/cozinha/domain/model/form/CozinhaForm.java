/**
 * 
 */
package br.com.viafood.cozinha.domain.model.form;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

/**
 * @author david
 *
 */
@Getter
@Setter
public class CozinhaForm {

	@NotBlank
	private String nome;
	
}
