/**
 * 
 */
package br.com.viafood.estado.domain.model.form;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

/**
 * @author david
 *
 */
@Getter
@Setter
public class EstadoForm {

	@NotBlank
	private String nome;
}
