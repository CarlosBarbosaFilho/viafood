/**
 * 
 */
package br.com.viafood.cozinha.asserts;

import br.com.viafood.util.ReadJsonFileResource;

/**
 * @author david
 *
 */
public abstract class AssertsJsonFilesCozinha {

	public static String retornaCozinhaJson() {
		return ReadJsonFileResource.getContentFromResource("/json/cozinha/cozinha_correto_japonesa.json");
	}
}
