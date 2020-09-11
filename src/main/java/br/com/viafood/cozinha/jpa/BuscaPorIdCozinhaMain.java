/**
 * 
 */
package br.com.viafood.cozinha.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import br.com.viafood.ViaFoodApplication;
import br.com.viafood.cozinha.domain.model.Cozinha;
import br.com.viafood.cozinha.domain.repository.CozinhaRepository;

/**
 * @author cbgomes
 *
 */
public class BuscaPorIdCozinhaMain {
	public static void main(String[] args) {
		
		ApplicationContext applicationContext = new SpringApplicationBuilder(ViaFoodApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);

		CozinhaRepository cozinhaRepository = applicationContext.getBean(CozinhaRepository.class);
		
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> BUSCANDO A COZINHA POR ID");
		Cozinha cozinha = cozinhaRepository.getById(1L); 
		System.out.println(">>>>>>>>>>>>>>> A CPZINHA RETORNADA EH "  + cozinha.getNome());
		
	}

}
