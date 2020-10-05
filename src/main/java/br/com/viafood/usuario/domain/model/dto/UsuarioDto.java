/**
 * 
 */
package br.com.viafood.usuario.domain.model.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author david
 *
 */
@Getter
@Setter
public class UsuarioDto {
	
	private Long id;

	private String nome;

	private String email;

}
