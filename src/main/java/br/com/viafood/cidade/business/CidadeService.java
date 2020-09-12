/**
 * 
 */
package br.com.viafood.cidade.business;

import java.util.List;

import br.com.viafood.cidade.domain.model.Cidade;

/**
 * @author cbgomes
 *
 */
public interface CidadeService {

	public List<Cidade> list();

	public Cidade save(Cidade cidade);

	public Cidade getById(Long id);

	public void remove(Long id);
}
