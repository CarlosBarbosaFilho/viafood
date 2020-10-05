/**
 * 
 */
package br.com.viafood.pagamento.business;

import java.util.List;

import br.com.viafood.pagamento.domain.model.FormaPagamento;

/**
 * @author david
 *
 */
public interface FormasPagamentosService {

	public List<FormaPagamento> list();

	public FormaPagamento save(FormaPagamento formaPagamento);

	public FormaPagamento getById(Long id);

	public void remove(Long id);
}
