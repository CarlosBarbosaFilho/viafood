/**
 * 
 */
package br.com.viafood.cidade.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import br.com.viafood.ViaFoodApplication;
import br.com.viafood.cidade.domain.repository.CidadeRespository;

/**
 * @author cbgomes
 *
 */
public class ConsultarCidadeMain {
	public static void main(String[] args) {
		

		ApplicationContext applicationContext = new SpringApplicationBuilder(ViaFoodApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);

		CidadeRespository cidadeRepository = applicationContext.getBean(CidadeRespository.class);
		
		cidadeRepository.findAll().stream().forEach(c -> {
			System.out.println(">>>>>>>>>>>>>>>>> " + c.getNome());
		});
		
		
	}

}
