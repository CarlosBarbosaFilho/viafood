/**
 * 
 */
package br.com.viafood.restaurante.infrastructure.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.Predicate;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import br.com.viafood.restaurante.domain.model.Restaurante;

/**
 * @author cbgomes
 *
 */
@Repository
public class RestauranteRepositoryCustomImpl implements RestauranteRepositoryCustom {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public  List<Restaurante> getByNomeAndTaxaInicialTaxaFinal(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal){

		var builder = manager.getCriteriaBuilder();

		var criteria = builder.createQuery(Restaurante.class);
		var root = criteria.from(Restaurante.class);
		
		var predicates = new ArrayList<Predicate>();
		if(StringUtils.hasLength(nome)) {
			predicates.add(builder.like(root.get("nome"), "%"+nome+"%"));			
		}
		
		if(StringUtils.isEmpty(taxaInicial)) {
			predicates.add(builder.greaterThanOrEqualTo(root.get("taxaFrete"), taxaInicial));			
		}
		
		if(StringUtils.isEmpty(taxaFinal)) {
			predicates.add(builder.lessThanOrEqualTo(root.get("taxaFrete"), taxaFinal));
		}
		
		criteria.where(predicates.toArray(new Predicate[0]));
		
		TypedQuery<Restaurante> query = manager.createQuery(criteria);
		return query.getResultList();
	}

}














