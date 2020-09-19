/**
 * 
 */
package br.com.viafood.permissao.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.viafood.permissao.domain.model.Permissao;

/**
 * @author cbgomes
 *
 */
public interface PermissaoRepository extends JpaRepository<Permissao, Long>{

}
