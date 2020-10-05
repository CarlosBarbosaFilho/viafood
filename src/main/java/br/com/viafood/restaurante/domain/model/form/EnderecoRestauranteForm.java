/**
 * 
 */
package br.com.viafood.restaurante.domain.model.form;

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
public class EnderecoRestauranteForm {

	@NotBlank
	private String cep;
	
	@NotBlank
	private String logradurou;
	
	@NotBlank
	private String numero;
	
	private String complemento;

	@NotBlank
	private String bairro;
	
	@Valid
	@NotNull
	private CidadeIdEnderecoRestauranteForm cidade;
}
