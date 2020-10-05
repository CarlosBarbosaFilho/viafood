/**
 * 
 */
package br.com.viafood.restaurante.domain.model.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.viafood.cidade.domain.model.Cidade;
import br.com.viafood.cozinha.domain.model.Cozinha;
import br.com.viafood.restaurante.domain.model.Restaurante;
import br.com.viafood.restaurante.domain.model.form.RestauranteForm;

/**
 * @author david
 *
 */
@Component
public class RestauranteConverterForm {
	

	@Autowired
	private ModelMapper modelMapper;

	public Restaurante ToRestauranteForm(final RestauranteForm form) {
		return this.modelMapper.map(form, Restaurante.class);
	}
	
	public void copyRestauranteFormToRestaurante(RestauranteForm restauranteForm, Restaurante restaurante) {
		restaurante.setCozinha(new Cozinha());
		if(restaurante.getEndereco() != null) {
			restaurante.getEndereco().setCidade(new Cidade());
		}
		this.modelMapper.map(restauranteForm, restaurante);
	}
}
