/**
 * 
 */
package br.com.viafood.restaurante.exception;

import br.com.viafood.exceptions.exception.EntidadeNaoEncontradaException;
import br.com.viafood.utils.constantes.Constantes;

/**
 * @author cbgomes
 *
 */
public class RestauranteNaoEncotradoExeption extends EntidadeNaoEncontradaException  {
	private static final long serialVersionUID = 1L;

	public RestauranteNaoEncotradoExeption(String msg) {
		super(msg);
	}
	
	public RestauranteNaoEncotradoExeption(Long idRestaurante) {
		this(String.format(Constantes.RESTAURANTE_NAO_ENCONTRADA, idRestaurante));
	}
}
