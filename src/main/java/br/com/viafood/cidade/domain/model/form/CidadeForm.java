/**
 * 
 */
package br.com.viafood.cidade.domain.model.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.viafood.estado.domain.model.form.EstadoForm;
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
	
	@NotNull
	private EstadoForm estado;
}
