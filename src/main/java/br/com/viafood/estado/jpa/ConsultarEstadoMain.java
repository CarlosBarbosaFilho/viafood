/**
 * 
 */
package br.com.viafood.estado.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import br.com.viafood.ViaFoodApplication;
import br.com.viafood.estado.domain.repository.EstadoRepository;

/**
 * @author cbgomes
 *
 */
public class ConsultarEstadoMain {
	public static void main(String[] args) {
		
		ApplicationContext applicationContext = new SpringApplicationBuilder(ViaFoodApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);

		EstadoRepository estadoRepository = applicationContext.getBean(EstadoRepository.class);
		
		estadoRepository.list().stream().forEach(c -> {
			System.out.println(">>>>>>>>>>>>>>>>> " + c.getNome());
		});
		

	}

}
