/**
 * 
 */
package br.com.viafood.usuario.domain.model.form;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

/**
 * @author david
 *
 */
@Getter
@Setter
public class UsuarioFormAtualizarSenha {

	@NotNull
	private String senhaAtual;
	
	@NotNull
	private String novaSenha;
}
