/**
 * 
 */
package br.com.viafood.restaurante.infrastructure.specification;

import java.math.BigDecimal;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import br.com.viafood.restaurante.domain.model.Restaurante;

/**
 * @author cbgomes
 *
 */
public class RestauranteFreteGratisSpec implements Specification<Restaurante>{
	private static final long serialVersionUID = 1L;

	@Override
	public Predicate toPredicate(Root<Restaurante> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		return builder.equal(root.get("taxaFrete"), BigDecimal.ZERO);
	}

}
