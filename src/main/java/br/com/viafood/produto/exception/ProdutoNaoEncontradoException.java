/**
 * 
 */
package br.com.viafood.produto.exception;

import br.com.viafood.exceptions.exception.EntidadeNaoEncontradaException;
import br.com.viafood.utils.constantes.Constantes;

/**
 * @author cbgomes
 *
 */
public class ProdutoNaoEncontradoException extends EntidadeNaoEncontradaException{
	private static final long serialVersionUID = 1L;

	public ProdutoNaoEncontradoException(String msg) {
		super(msg);
	}
	
	public ProdutoNaoEncontradoException(Long idProduto) {
		this(String.format(Constantes.PRODUTO_NAO_ENCONTRADA, idProduto));
	}
}
