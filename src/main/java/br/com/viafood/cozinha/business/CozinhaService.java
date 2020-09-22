/**
 * 
 */
package br.com.viafood.cozinha.business;

import java.util.List;

import br.com.viafood.cozinha.domain.model.Cozinha;

/**
 * @author cbgomes
 *
 */
public interface CozinhaService {

	public List<Cozinha> list();

	public Cozinha save(Cozinha cozinha);

	public Cozinha getById(Long id);

	public void remove(Long id);
	
	public Cozinha getByNome(String nome);
	
	public boolean existsCozinha(String nome);
	
}
