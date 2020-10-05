/**
 * 
 */
package br.com.viafood.grupo.domain.model.form;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

/**
 * @author david
 *
 */
@Getter
@Setter
public class GrupoForm {

	@NotBlank
	private String descricao;
}
