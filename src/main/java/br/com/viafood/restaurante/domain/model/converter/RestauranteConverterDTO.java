/**
 * 
 */
package br.com.viafood.restaurante.domain.model.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.viafood.restaurante.domain.model.Restaurante;
import br.com.viafood.restaurante.domain.model.dto.RestauranteDto;

/**
 * @author david
 *
 */
@Component
public class RestauranteConverterDTO {

	@Autowired
	private ModelMapper modelMapper;
	
	public List<RestauranteDto> restaurantesDtos(List<Restaurante> restaurantes){
		return restaurantes.stream()
				.map(restaurante -> ToDto(restaurante))
				.collect(Collectors.toList());
	}

	public RestauranteDto ToDto(final Restaurante restaurante) {
		return this.modelMapper.map(restaurante, RestauranteDto.class);
	}
	
}
