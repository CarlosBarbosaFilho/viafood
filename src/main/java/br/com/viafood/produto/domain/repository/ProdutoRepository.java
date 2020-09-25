/**
 * 
 */
package br.com.viafood.produto.domain.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import br.com.viafood.produto.domain.model.Produto;
import br.com.viafood.utils.infrastrutura.CustomJpaRepository;

/**
 * @author cbgomes
 *
 */
@Repository
public interface ProdutoRepository extends CustomJpaRepository<Produto, Long>, JpaSpecificationExecutor<Produto>  {

}
