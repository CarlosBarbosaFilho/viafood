/**
 * 
 */
package br.com.viafood.restaurante.infrastructure.specification;

import org.springframework.data.jpa.domain.Specification;

import br.com.viafood.restaurante.domain.model.Restaurante;

/**
 * @author cbgomes
 *
 */
public class RestauranteSpec {

	public static Specification<Restaurante> freteGratis(){
		return new RestauranteFreteGratisSpec();
	}
	
	public static Specification<Restaurante> nomeSemelhante(String nome){
		return new RestauranteComNomeSemelhanteSpec(nome);
	}
}
