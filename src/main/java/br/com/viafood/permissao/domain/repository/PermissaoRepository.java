/**
 * 
 */
package br.com.viafood.permissao.domain.repository;

import java.util.List;

import br.com.viafood.permissao.domain.model.Permissao;

/**
 * @author cbgomes
 *
 */
public interface PermissaoRepository {

	public List<Permissao> list();

	public void save(Permissao permissao);

	public Permissao getById(Long id);

	public void remove(Permissao permissao);
}
