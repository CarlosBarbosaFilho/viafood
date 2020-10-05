/**
 * 
 */
package br.com.viafood.restaurante.domain.model.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author david
 *
 */
@Getter
@Setter
public class EnderecoRestauranteDto {

	private String cep;
	
	private String logradurou;
	
	private String numero;
	
	private String complemento;

	private String bairro;
	
	private CidadeEnderecoRestauranteDto cidade;
}
