/**
 * 
 */
package br.com.viafood.pagamento.domain.model.conveter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.viafood.pagamento.domain.model.FormaPagamento;
import br.com.viafood.pagamento.domain.model.dto.FormaPagamentoDto;

/**
 * @author david
 *
 */
@Component
public class FormaPagamentoConverterDTO {

	@Autowired
	private ModelMapper modelMapper;
	
	public List<FormaPagamentoDto> formasPagamentosDtos(List<FormaPagamento> formasPagamentos){
		return formasPagamentos.stream()
				.map(fpagamento -> ToDto(fpagamento))
				.collect(Collectors.toList());
	}

	public FormaPagamentoDto ToDto(final FormaPagamento formaPagamento) {
		return this.modelMapper.map(formaPagamento, FormaPagamentoDto.class);
	}
	
}
