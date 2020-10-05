/**
 * 
 */
package br.com.viafood.cidade.domain.model.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.viafood.cidade.domain.model.Cidade;
import br.com.viafood.cidade.domain.model.form.CidadeForm;
import br.com.viafood.estado.domain.model.Estado;

/**
 * @author david
 *
 */
@Component
public class CidadeConverterForm {
	

	@Autowired
	private ModelMapper modelMapper;

	public Cidade ToCidadeForm(final CidadeForm form) {
		return this.modelMapper.map(form, Cidade.class);
	}
	
	public void copyCidadeFormToCidade(CidadeForm cidadeForm, Cidade cidade) {
		cidade.setEstado(new Estado());
		this.modelMapper.map(cidadeForm,cidade);
	}
}
