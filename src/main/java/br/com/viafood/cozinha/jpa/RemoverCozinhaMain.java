/**
 * 
 */
package br.com.viafood.cozinha.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import br.com.viafood.ViaFoodApplication;
import br.com.viafood.cozinha.domain.repository.CozinhaRepository;

/**
 * @author cbgomes
 *
 */
public class RemoverCozinhaMain {
	public static void main(String[] args) {
		
		ApplicationContext applicationContext = new SpringApplicationBuilder(ViaFoodApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);

		CozinhaRepository cozinhaRepository = applicationContext.getBean(CozinhaRepository.class);
		
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> REMOVENDO UMA COZINHA NA BASE DE DADOS");
		cozinhaRepository.remove(1L);
		
	}

}
