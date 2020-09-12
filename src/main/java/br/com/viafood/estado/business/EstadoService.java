/**
 * 
 */
package br.com.viafood.estado.business;

import java.util.List;

import br.com.viafood.estado.domain.model.Estado;

/**
 * @author cbgomes
 *
 */
public interface EstadoService {


	public List<Estado> list();

	public Estado save(Estado estado);

	public Estado getById(Long id);

	public void remove(Long id);
}
