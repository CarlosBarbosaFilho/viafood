package br.com.viafood.pagamento.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import br.com.viafood.ViaFoodApplication;
import br.com.viafood.pagamento.domain.repository.FormaPagamentoRepository;

public class ConsultarFormaPagamento {

public static void main(String[] args) {
		
		ApplicationContext applicationContext = new SpringApplicationBuilder(ViaFoodApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);

		FormaPagamentoRepository formaPagamentoRepository = applicationContext.getBean(FormaPagamentoRepository.class);
		
		formaPagamentoRepository.list().stream().forEach(c -> {
			System.out.println(">>>>>>>>>>>>>>>>> " + c.getDescricao());
		});
	
	}
	
}
