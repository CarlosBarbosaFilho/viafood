/**
 * 
 */
package br.com.viafood.cozinha.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.viafood.cozinha.domain.model.Cozinha;

/**
 * @author cbgomes
 *
 */
@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {
	
	
	public Cozinha nomeContaining(String nome);
	
	public boolean existsByNome(String nome);
}