/**
 * 
 */
package br.com.viafood.restaurante.infrastructure;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.viafood.cozinha.exception.EntidadeNaoEncontrada;
import br.com.viafood.restaurante.domain.model.Restaurante;
import br.com.viafood.restaurante.domain.repository.RestauranteRepository;

/**
 * @author cbgomes
 *
 */
@Component
public class RestauranteRepositoryImpl implements RestauranteRepository {
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Restaurante> list() {
		return this.manager.createQuery("From Restaurante", Restaurante.class).getResultList();
	}

	@Override
	@Transactional
	public Restaurante save(Restaurante restaurante) {
		return this.manager.merge(restaurante);
	}

	@Override
	public Restaurante getById(Long id) {
		return this.manager.find(Restaurante.class, id);
	}

	@Override
	@Transactional
	public void remove(Long id) {
		Restaurante restaurante = this.getById(id);
		if(restaurante == null) {
			throw new EmptyResultDataAccessException(1);
		}
		this.manager.remove(restaurante);
	}

}
