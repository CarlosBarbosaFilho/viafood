/**
 * 
 */
package br.com.viafood.cidade.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.viafood.cidade.domain.model.Cidade;
import br.com.viafood.cidade.domain.repository.CidadeRespository;

/**
 * @author cbgomes
 *
 */
@Component
public class CidadeRerepositoryImpl implements CidadeRespository {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Cidade> list() {
		return this.manager.createQuery("From Cidade", Cidade.class).getResultList();
	}

	@Override
	@Transactional
	public void save(Cidade cidade) {
		this.manager.merge(cidade);
	}

	@Override
	public Cidade getById(Long id) {
		return this.manager.find(Cidade.class, id);
	}

	@Override
	@Transactional
	public void remove(Cidade cidade) {
		this.manager.remove(this.getById(cidade.getId()));
	}

}
