/**
 * 
 */
package br.com.viafood.estado.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.viafood.estado.domain.model.Estado;

/**
 * @author cbgomes
 *
 */
public interface EstadoRepository extends JpaRepository<Estado, Long> {
}
