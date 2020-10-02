/**
 * 
 */
package br.com.viafood.restaurante.domain.model.form;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

/**
 * @author david
 *
 */
@Getter
@Setter
public class CozinhaIdRestauranteForm {

	@NotNull
	private Long id;
	
}
