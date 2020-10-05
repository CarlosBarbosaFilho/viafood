/**
 * 
 */
package br.com.viafood.usuario.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import br.com.viafood.restaurante.domain.model.Restaurante;
import br.com.viafood.restaurante.infrastructure.repository.RestauranteRepositoryCustom;
import br.com.viafood.usuario.domain.model.Usuario;
import br.com.viafood.utils.infrastrutura.CustomJpaRepository;

/**
 * @author david
 *
 */
@Repository
public interface UsuarioRepository extends CustomJpaRepository<Usuario, Long>, JpaSpecificationExecutor<Usuario> {

	Optional<Usuario> findByEmail(String email);
}
