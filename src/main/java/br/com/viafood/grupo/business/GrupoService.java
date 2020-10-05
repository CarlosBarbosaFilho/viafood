/**
 * 
 */
package br.com.viafood.grupo.business;

import java.util.List;

import br.com.viafood.grupo.domain.model.Grupo;

/**
 * @author david
 *
 */
public interface GrupoService {

	public List<Grupo> list();

	public Grupo save(Grupo grupo);

	public Grupo getById(Long id);

	public void remove(Long id);
	
	public Grupo getByDescricao(String nome);
	
}
