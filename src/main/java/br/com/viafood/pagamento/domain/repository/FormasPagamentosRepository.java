/**
 * 
 */
package br.com.viafood.pagamento.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.viafood.pagamento.domain.model.FormaPagamento;

/**
 * @author cbgomes
 *
 */
@Repository
public interface FormasPagamentosRepository extends JpaRepository<FormaPagamento, Long>{
	
}
