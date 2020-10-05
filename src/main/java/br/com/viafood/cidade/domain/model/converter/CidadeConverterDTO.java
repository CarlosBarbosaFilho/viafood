/**
 * 
 */
package br.com.viafood.cidade.domain.model.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.viafood.cidade.domain.model.Cidade;
import br.com.viafood.cidade.domain.model.dto.CidadeDto;

/**
 * @author david
 *
 */
@Component
public class CidadeConverterDTO {

	@Autowired
	private ModelMapper modelMapper;
	
	public List<CidadeDto> cidadeDtos(List<Cidade> cidades){
		return cidades.stream()
				.map(cidade -> ToDto(cidade))
				.collect(Collectors.toList());
	}

	public CidadeDto ToDto(final Cidade cidade) {
		return this.modelMapper.map(cidade, CidadeDto.class);
	}
	
}
