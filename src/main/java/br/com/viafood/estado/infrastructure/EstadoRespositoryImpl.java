/**
 * 
 */
package br.com.viafood.estado.infrastructure;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.ResourceAccessException;

import br.com.viafood.estado.domain.model.Estado;
import br.com.viafood.estado.domain.repository.EstadoRepository;

/**
 * @author cbgomes
 *
 */
@Component
public class EstadoRespositoryImpl implements EstadoRepository {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Estado> list() {
		return this.manager.createQuery("From Estado", Estado.class).getResultList();
	}

	@Override
	@Transactional
	public Estado save(Estado estado) {
		return this.manager.merge(estado);

	}

	@Override
	public Estado getById(Long id) {
		Estado estado = this.manager.find(Estado.class, id);
		if(estado == null) {
			throw new ResourceAccessException(String.format("Estado com id %d não localizado ou não existe", id));			
		}
		return estado;
	}

	@Override
	@Transactional
	public void remove(Long id) {
		Estado estado = this.getById(id);
		if (estado == null) {
			throw new EmptyResultDataAccessException(1);
		}
		this.manager.remove(estado);
	}

}
