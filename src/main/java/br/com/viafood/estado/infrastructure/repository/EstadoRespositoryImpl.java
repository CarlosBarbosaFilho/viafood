/**
 * 
 */
package br.com.viafood.estado.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.viafood.estado.domain.model.Estado;
import br.com.viafood.estado.domain.repository.EstadoRepository;

/**
 * @author cbgomes
 *
 */
@Component
public class EstadoRespositoryImpl implements EstadoRepository{
	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Estado> list() {
		return this.manager.createQuery("From Estado", Estado.class).getResultList();
	}

	@Override
	@Transactional
	public void save(Estado estado) {
		this.manager.merge(estado);
		
	}

	@Override
	public Estado getById(Long id) {
		return this.manager.find(Estado.class, id);
	}

	@Override
	@Transactional
	public void remove(Estado estado) {
		this.manager.remove(this.getById(estado.getId()));
		
	}

}
