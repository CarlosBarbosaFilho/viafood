/**
 * 
 */
package br.com.viafood.permissao.infrastructure;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import br.com.viafood.permissao.domain.model.Permissao;

/**
 * @author cbgomes
 *
 */
@Component
public class PermissaoRepositoryImpl {
	
	@PersistenceContext
	private EntityManager manager;

	public List<Permissao> list() {
		return this.manager.createQuery("From Permissao", Permissao.class).getResultList();
	}

	public void save(Permissao permissao) {
		this.manager.merge(permissao);

	}

	public Permissao getById(Long id) {
		return this.manager.find(Permissao.class,id);
	}

	public void remove(Permissao permissao) {
		this.manager.remove(this.getById(permissao.getId()));
	}

}
