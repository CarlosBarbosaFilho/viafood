/**
 * 
 */
package br.com.viafood.endereco.domain.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.viafood.endereco.domain.Endereco;
import br.com.viafood.endereco.domain.dto.EnderecoDto;

/**
 * @author david
 *
 */
@Component
public class EnderecoConverterDTO {

	@Autowired
	private ModelMapper modelMapper;
	
	public List<EnderecoDto> restaurantesDtos(List<Endereco> enderecos){
		return enderecos.stream()
				.map(endereco -> ToDto(endereco))
				.collect(Collectors.toList());
	}

	public EnderecoDto ToDto(final Endereco endereco) {
		return this.modelMapper.map(endereco, EnderecoDto.class);
	}
	
}
