/**
 * 
 */
package br.com.viafood.permissao.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import br.com.viafood.ViaFoodApplication;
import br.com.viafood.permissao.domain.repository.PermissaoRepository;

/**
 * @author cbgomes
 *
 */
public class ConsultarPermissaoPagamentoMain {
	public static void main(String[] args) {
		
		ApplicationContext applicationContext = new SpringApplicationBuilder(ViaFoodApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);

		PermissaoRepository permissaoRepository = applicationContext.getBean(PermissaoRepository.class);
		
		permissaoRepository.list().stream().forEach(c -> {
			System.out.println(">>>>>>>>>>>>>>>>> " + c.getDescricao());
		});
	
	}

}
