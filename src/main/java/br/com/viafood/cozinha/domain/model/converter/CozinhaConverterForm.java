/**
 * 
 */
package br.com.viafood.cozinha.domain.model.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.viafood.cozinha.domain.model.Cozinha;
import br.com.viafood.cozinha.domain.model.form.CozinhaForm;

/**
 * @author david
 *
 */
@Component
public class CozinhaConverterForm {
	

	@Autowired
	private ModelMapper modelMapper;

	public Cozinha ToCozinhaForm(final CozinhaConverterForm form) {
		return this.modelMapper.map(form, Cozinha.class);
	}
	
	public void copyCozinhaFormToCozinha(CozinhaForm cozinhaForm, Cozinha cozinha) {
		this.modelMapper.map(cozinhaForm,cozinha);
	}
}
