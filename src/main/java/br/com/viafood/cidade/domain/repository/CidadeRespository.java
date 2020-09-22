/**
 * 
 */
package br.com.viafood.cidade.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.viafood.cidade.domain.model.Cidade;

/**
 * @author cbgomes
 *
 */
@Repository
public interface CidadeRespository extends JpaRepository<Cidade, Long> {

}
