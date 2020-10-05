/**
 * 
 */
package br.com.viafood.grupo.domain.model.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.viafood.grupo.domain.model.Grupo;
import br.com.viafood.grupo.domain.model.form.GrupoForm;

/**
 * @author david
 *
 */
@Component
public class GrupoConverterForm {
	

	@Autowired
	private ModelMapper modelMapper;

	public Grupo ToGrupoForm(final GrupoForm form) {
		return this.modelMapper.map(form, Grupo.class);
	}
	
	public void copyGrupoFormToGrupo(GrupoForm grupoForm, Grupo grupo) {
		this.modelMapper.map(grupoForm,grupo);
	}
}
