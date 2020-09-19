/**
 * 
 */
package br.com.viafood.utils.infrastrutura;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author cbgomes
 *
 */
@NoRepositoryBean
public interface CustomJpaRepository<T,ID> extends JpaRepository<T, ID> {

	Optional<T> buscaPrimeiroCadastro();
}
