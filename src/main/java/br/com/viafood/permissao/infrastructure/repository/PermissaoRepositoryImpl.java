/**
 * 
 */
package br.com.viafood.permissao.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.viafood.permissao.domain.model.Permissao;
import br.com.viafood.permissao.domain.repository.PermissaoRepository;

/**
 * @author cbgomes
 *
 */
@Component
public class PermissaoRepositoryImpl implements PermissaoRepository {
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Permissao> list() {
		return this.manager.createQuery("From Permissao", Permissao.class).getResultList();
	}

	@Override
	@Transactional
	public void save(Permissao permissao) {
		this.manager.merge(permissao);

	}

	@Override
	public Permissao getById(Long id) {
		return this.manager.find(Permissao.class,id);
	}

	@Override
	@Transactional
	public void remove(Permissao permissao) {
		this.manager.remove(this.getById(permissao.getId()));
	}

}
