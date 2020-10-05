/**
 * 
 */
package br.com.viafood.core.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.viafood.endereco.domain.Endereco;
import br.com.viafood.restaurante.domain.model.dto.EnderecoRestauranteDto;

/**
 * @author david
 *
 */
@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {
		var modelMapper = new ModelMapper();
		
		var enderecoToEnderecoTypeMapper = modelMapper.createTypeMap(Endereco.class, EnderecoRestauranteDto.class);
		
		enderecoToEnderecoTypeMapper.<String>addMapping(
				src -> src.getCidade().getEstado().getNome(),
				(destino, valor) -> destino.getCidade().setEstado(valor));
		
		return modelMapper;
	}
}
