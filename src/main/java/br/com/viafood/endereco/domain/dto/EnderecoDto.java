/**
 * 
 */
package br.com.viafood.endereco.domain.dto;

import br.com.viafood.restaurante.domain.model.dto.CidadeEnderecoRestauranteDto;
import lombok.Getter;
import lombok.Setter;

/**
 * @author david
 *
 */
@Getter
@Setter
public class EnderecoDto {

	private String cep;
	
	private String logradurou;
	
	private String numero;
	
	private String complemento;

	private String bairro;
	
	private CidadeEnderecoRestauranteDto cidade;
}
