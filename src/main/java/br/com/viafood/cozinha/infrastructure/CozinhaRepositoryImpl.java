/**
 * 
 */
package br.com.viafood.cozinha.infrastructure;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.viafood.cozinha.domain.model.Cozinha;
import br.com.viafood.cozinha.domain.repository.CozinhaRepository;

/**
 * @author cbgomes
 *
 */
@Component
public class CozinhaRepositoryImpl implements CozinhaRepository{

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Cozinha> list() {
		return manager.createQuery("From Cozinha", Cozinha.class).getResultList();
	}

	@Transactional
	@Override
	public void save(final Cozinha cozinha) {
		this.manager.merge(cozinha);
	}

	@Override
	public Cozinha getById(final Long id) {
		return this.manager.find(Cozinha.class, id);
	}

	
	@Transactional
	@Override
	public void remove(final Long id)  {
		Cozinha cozinha = this.getById(id);
		if (cozinha == null) {
			throw new EmptyResultDataAccessException(1);
		}
		this.manager.remove(cozinha);
	}
}
