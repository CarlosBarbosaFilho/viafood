/**
 * 
 */
package br.com.viafood.pagamento.domain.model.conveter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.viafood.pagamento.domain.model.FormaPagamento;
import br.com.viafood.pagamento.domain.model.form.FormaPagamentoForm;

/**
 * @author david
 *
 */
@Component
public class FormaPagamentoConverterForm {
	

	@Autowired
	private ModelMapper modelMapper;

	public FormaPagamento ToFormaPagamentoFormFormaPagamento(final FormaPagamentoForm formaPagamentoForm) {
		return this.modelMapper.map(formaPagamentoForm, FormaPagamento.class);
	}
	
	public void copyFormaPagamentoFormToFormaPagamento(FormaPagamentoForm formaPagamentoForm, FormaPagamento formaPagamento) {
		this.modelMapper.map(formaPagamentoForm,formaPagamento);
	}
}
