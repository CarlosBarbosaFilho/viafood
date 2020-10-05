/**
 * 
 */
package br.com.viafood.endereco.domain.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.viafood.cidade.domain.model.Cidade;
import br.com.viafood.endereco.domain.Endereco;
import br.com.viafood.endereco.domain.form.EnderecoForm;

/**
 * @author david
 *
 */
@Component
public class EnderecoConverterForm {
	

	@Autowired
	private ModelMapper modelMapper;

	public Endereco ToEnderecoForm(final EnderecoForm form) {
		return this.modelMapper.map(form, Endereco.class);
	}
	
	public void copyEnderecoFormToEndereco(EnderecoForm enderecoForm, Endereco endereco) {
		endereco.setCidade(new Cidade());
		this.modelMapper.map(enderecoForm, endereco);
	}
}
