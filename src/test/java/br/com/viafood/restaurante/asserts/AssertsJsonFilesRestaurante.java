/**
 * 
 */
package br.com.viafood.restaurante.asserts;

import br.com.viafood.util.ReadJsonFileResource;

/**
 * @author david
 *
 */
public abstract class AssertsJsonFilesRestaurante {

	public static String retornaRestauranteCorretoJson() {
		return ReadJsonFileResource.getContentFromResource("/json/restaurante/restaurante_correto_brasileiro.json");
	}
	
	public static String retornaRestauranteIncorretoSemCozinhaVinculadaJson() {
		return ReadJsonFileResource.getContentFromResource("/json/restaurante/restaurante_incorreto_sem_cozinha_brasileiro.json");
	}
	
	public static String retornaRestauranteIncorretoSemNomeInformadoJson() {
		return ReadJsonFileResource.getContentFromResource("/json/restaurante/restaurante_incorreto_sem_cozinha_brasileiro.json");
	}
	
	public static String retornaRestauranteIncorretoTaxaFreteNegativaJson() {
		return ReadJsonFileResource.getContentFromResource("/json/restaurante/restaurante_incorreto_taxafrete_negativa_brasileiro.json");
	}
	
	public static String retornaMensagemFreteGratisRestauranteIncorretoTaxaFreteIgualAZeroJson() {
		return ReadJsonFileResource.getContentFromResource("/json/restaurante/restaurante_incorreto_taxafrete_igual_a_zero_brasileiro.json");
	}
	
}
