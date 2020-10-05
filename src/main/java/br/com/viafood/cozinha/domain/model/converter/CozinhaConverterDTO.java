/**
 * 
 */
package br.com.viafood.cozinha.domain.model.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.viafood.cozinha.domain.model.Cozinha;
import br.com.viafood.cozinha.domain.model.dto.CozinhaDto;

/**
 * @author david
 *
 */
@Component
public class CozinhaConverterDTO {

	@Autowired
	private ModelMapper modelMapper;
	
	public List<CozinhaDto> cozinhasDtos(List<Cozinha> cozinhas){
		return cozinhas.stream()
				.map(cozinha -> ToDto(cozinha))
				.collect(Collectors.toList());
	}

	public CozinhaDto ToDto(final Cozinha cozinha) {
		return this.modelMapper.map(cozinha, CozinhaDto.class);
	}
	
}
