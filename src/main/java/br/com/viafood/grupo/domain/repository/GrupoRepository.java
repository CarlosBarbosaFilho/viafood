/**
 * 
 */
package br.com.viafood.grupo.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.viafood.grupo.domain.model.Grupo;

/**
 * @author david
 *
 */
@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Long> {

	public Grupo findByDescricao(String descricao);
}
