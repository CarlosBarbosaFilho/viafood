/**
 * 
 */
package br.com.viafood.grupo.domain.model.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.viafood.grupo.domain.model.Grupo;
import br.com.viafood.grupo.domain.model.dto.GrupoDto;

/**
 * @author david
 *
 */
@Component
public class GrupoConverterDTO {

	@Autowired
	private ModelMapper modelMapper;
	
	public List<GrupoDto> cozinhasDtos(List<Grupo> grupos){
		return grupos.stream()
				.map(grupo -> ToDto(grupo))
				.collect(Collectors.toList());
	}

	public GrupoDto ToDto(final Grupo grupo) {
		return this.modelMapper.map(grupo, GrupoDto.class);
	}
	
}
