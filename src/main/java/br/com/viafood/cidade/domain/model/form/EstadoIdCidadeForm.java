/**
 * 
 */
package br.com.viafood.cidade.domain.model.form;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

/**
 * @author david
 *
 */
@Getter
@Setter
public class EstadoIdCidadeForm {

	@NotNull
	private Long id; 
}
