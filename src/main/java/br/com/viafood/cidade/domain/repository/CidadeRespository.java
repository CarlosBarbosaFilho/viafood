/**
 * 
 */
package br.com.viafood.cidade.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.viafood.cidade.domain.model.Cidade;

/**
 * @author cbgomes
 *
 */
public interface CidadeRespository extends JpaRepository<Cidade, Long> {

}
