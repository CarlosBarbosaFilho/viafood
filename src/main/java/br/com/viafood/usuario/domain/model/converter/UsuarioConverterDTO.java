/**
 * 
 */
package br.com.viafood.usuario.domain.model.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.viafood.usuario.domain.model.Usuario;
import br.com.viafood.usuario.domain.model.dto.UsuarioDto;

/**
 * @author david
 *
 */
@Component
public class UsuarioConverterDTO {

	@Autowired
	private ModelMapper modelMapper;
	
	public List<UsuarioDto> restaurantesDtos(List<Usuario> usuarios){
		return usuarios.stream()
				.map(usuario -> ToDto(usuario))
				.collect(Collectors.toList());
	}

	public UsuarioDto ToDto(final Usuario usuario) {
		return this.modelMapper.map(usuario, UsuarioDto.class);
	}
	
}
