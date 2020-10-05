/**
 * 
 */
package br.com.viafood.usuario.domain.model.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.viafood.usuario.domain.model.Usuario;
import br.com.viafood.usuario.domain.model.form.UsuarioForm;

/**
 * @author david
 *
 */
@Component
public class UsuarioConverterForm {
	

	@Autowired
	private ModelMapper modelMapper;

	public Usuario ToUsuarioForm(final UsuarioForm form) {
		return this.modelMapper.map(form, Usuario.class);
	}
	
	public void copyUsuarioFormToUsuario(UsuarioForm usuarioForm, Usuario usuario) {
		this.modelMapper.map(usuarioForm, usuario);
	}
}
