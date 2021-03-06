/**
 * 
 */
package br.com.viafood.restaurante.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import br.com.viafood.ViaFoodApplication;
import br.com.viafood.restaurante.domain.repository.RestauranteRepository;

/**
 * @author cbgomes
 *
 */
public class ConsultaRestauranteMain {
	public static void main(String[] args) {
		
		ApplicationContext applicationContext = new SpringApplicationBuilder(ViaFoodApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);

		RestauranteRepository restauranteRepository = applicationContext.getBean(RestauranteRepository.class);
		
		restauranteRepository.findAll().stream().forEach(c -> {
			System.out.println(">>>>>>>>>>>>>>>>> " + c.getNome());
		});
	
	}

}
