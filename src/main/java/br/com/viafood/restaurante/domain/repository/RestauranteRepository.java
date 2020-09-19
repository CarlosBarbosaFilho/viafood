/**
 * 
 */
package br.com.viafood.restaurante.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.viafood.restaurante.domain.model.Restaurante;
import br.com.viafood.restaurante.infrastructure.repository.RestauranteRepositoryCustom;
import br.com.viafood.utils.infrastrutura.CustomJpaRepository;

/**
 * @author cbgomes
 *
 */
@Repository
public interface RestauranteRepository
		extends CustomJpaRepository<Restaurante, Long>, RestauranteRepositoryCustom, JpaSpecificationExecutor<Restaurante> {

	public List<Restaurante> findByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);

	public List<Restaurante> findByNomeContainingAndCozinhaId(String nome, @Param("id") Long idCozinha);

	public int countByCozinhaId(Long idCozinha);
	
	@Query("From Restaurante rs join fetch rs.cozinha left join fetch rs.formasPagamentos")
	public List<Restaurante> findAll();

}
