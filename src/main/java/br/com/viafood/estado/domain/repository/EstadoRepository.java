/**
 * 
 */
package br.com.viafood.estado.domain.repository;

import java.util.List;

import br.com.viafood.estado.domain.model.Estado;

/**
 * @author cbgomes
 *
 */
public interface EstadoRepository {


	public List<Estado> list();

	public void save(Estado estado);

	public Estado getById(Long id);

	public void remove(Estado estado);
}
