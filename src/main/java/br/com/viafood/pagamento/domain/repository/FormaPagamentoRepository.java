/**
 * 
 */
package br.com.viafood.pagamento.domain.repository;

import java.util.List;

import br.com.viafood.pagamento.domain.model.FormaPagamento;

/**
 * @author cbgomes
 *
 */
public interface FormaPagamentoRepository {

	public List<FormaPagamento> list();

	public void save(FormaPagamento formaPagamento);

	public FormaPagamento getById(Long id);

	public void remove(FormaPagamento formaPagamento);
}
