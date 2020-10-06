/**
 * 
 */
package br.com.viafood.restaurante.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.viafood.pagamento.domain.model.FormaPagamento;
import br.com.viafood.pagamento.domain.model.conveter.FormaPagamentoConverterDTO;
import br.com.viafood.pagamento.domain.model.conveter.FormaPagamentoConverterForm;
import br.com.viafood.pagamento.domain.model.dto.FormaPagamentoDto;
import br.com.viafood.restaurante.business.RestauranteService;

/**
 * @author david
 *
 */
@RestController
@RequestMapping("/api/v1")
public class RestauranteFormaPagamentoResourceRest {

	private final RestauranteService service;
	
	private final FormaPagamentoConverterDTO formaPagamentoConverterDTO;
	
	private final FormaPagamentoConverterForm formaPagamentoConverterForm; 

	@Autowired
	public RestauranteFormaPagamentoResourceRest(final RestauranteService service, 
			final FormaPagamentoConverterDTO formaPagamentoConverterDTO, final FormaPagamentoConverterForm formaPagamentoConverterForm) {
		this.service = service;
		this.formaPagamentoConverterDTO = formaPagamentoConverterDTO;
		this.formaPagamentoConverterForm = formaPagamentoConverterForm;
	}
	
	@GetMapping("/restaurantes/{idRestaurante}/formas-pagamentos")
	public List<FormaPagamentoDto> listFormasPagamentoRestauran(@PathVariable final Long idRestaurante){
		return this.formaPagamentoConverterDTO.formasPagamentosDtos(this.service.listaFormaPagamentoRestaurante(idRestaurante)); 
	}
}
