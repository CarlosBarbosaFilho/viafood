/**
 * 
 */
package br.com.viafood.restaurante.business;

import java.math.BigDecimal;
import java.util.List;

import br.com.viafood.restaurante.domain.model.Restaurante;

/**
 * @author cbgomes
 *
 */
public interface RestauranteService {

	public List<Restaurante> list();
	
	public List<Restaurante> listRestauranteFreteGratis(String nome);

	public Restaurante save(Restaurante restaurante);

	public Restaurante getById(Long id);
	
	public Restaurante buscaPrimeiroCadastro();

	public void remove(Long id);
	
	public List<Restaurante> listaRestaurantesPorFaixaDeTaxaFrete(BigDecimal taxaInicial, BigDecimal taxaFinal);
	
	public List<Restaurante> listaRestaurantePorNomeECozinha(String nome, Long idCozinha);
	
	public int countCozinhas(Long id);
	
	public List<Restaurante> recuperaRestaurantePorNomeEFaixaDeFrete(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal);
}
