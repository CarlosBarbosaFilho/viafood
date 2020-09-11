/**
 * 
 */
package br.com.viafood.cozinha.domain.repository;

import java.util.List;

import br.com.viafood.cozinha.domain.model.Cozinha;

/**
 * @author cbgomes
 *
 */

public interface CozinhaRepository {

	public List<Cozinha> list();

	public void save(Cozinha cozinha);

	public Cozinha getById(Long id);

	public void remove(Long id);
}
