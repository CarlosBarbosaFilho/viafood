/**
 * 
 */
package br.com.viafood.usuario.domain.model.form;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

/**
 * @author david
 *
 */
@Getter
@Setter
public class UsuarioForm {

	@NotBlank
	private String nome;

	@NotBlank
	private String email;

}
