/**
 * 
 */
package br.com.viafood.utils.infrastrutura;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

/**
 * @author cbgomes
 *
 */
public class CustomJpaRepositoryImpl<T,ID> extends SimpleJpaRepository<T, ID> implements CustomJpaRepository<T, ID>{
	
	private EntityManager manager;
	
	public CustomJpaRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.manager = entityManager;
	}

	@Override
	public Optional<T> buscaPrimeiroCadastro() {
		var jpql = " From " + getDomainClass().getName();
		T entity = manager.createQuery(jpql, getDomainClass())
			.setMaxResults(1)
			.getSingleResult();
		return Optional.ofNullable(entity);
	}

	@Override
	public void detached(T entity) {
		this.manager.detach(entity);
	}

}
