/**
 * 
 */
package br.com.viafood.cidade.domain.model.form;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

/**
 * @author david
 *
 */
@Getter
@Setter
public class CidadeForm {

	@NotBlank
	private String nome;
	
	@Valid
	@NotNull
	private EstadoIdCidadeForm estado;
}
