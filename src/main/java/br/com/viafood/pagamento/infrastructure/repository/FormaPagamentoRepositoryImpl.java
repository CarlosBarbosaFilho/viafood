/**
 * 
 */
package br.com.viafood.pagamento.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.viafood.pagamento.domain.model.FormaPagamento;
import br.com.viafood.pagamento.domain.repository.FormaPagamentoRepository;

/**
 * @author cbgomes
 *
 */
@Component
public class FormaPagamentoRepositoryImpl implements FormaPagamentoRepository {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<FormaPagamento> list() {
		return this.manager.createQuery("From FormaPagamento", FormaPagamento.class).getResultList();
	}

	@Override
	@Transactional
	public void save(FormaPagamento formaPagamento) {
		this.manager.merge(formaPagamento);
	}

	@Override
	public FormaPagamento getById(Long id) {
		return this.manager.find(FormaPagamento.class, id);
	}

	@Override
	@Transactional
	public void remove(FormaPagamento formaPagamento) {
		this.manager.remove(this.getById(formaPagamento.getId()));
	}

}
