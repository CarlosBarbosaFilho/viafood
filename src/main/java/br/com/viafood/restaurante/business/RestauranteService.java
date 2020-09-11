/**
 * 
 */
package br.com.viafood.restaurante.business;

import java.util.List;

import br.com.viafood.restaurante.domain.model.Restaurante;

/**
 * @author cbgomes
 *
 */
public interface RestauranteService {

	public List<Restaurante> list();

	public void save(Restaurante restaurante);

	public Restaurante getById(Long id);

	public void remove(Long id);
}
