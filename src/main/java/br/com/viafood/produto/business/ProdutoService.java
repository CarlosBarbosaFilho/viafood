/**
 * 
 */
package br.com.viafood.produto.business;

import java.util.List;

import br.com.viafood.produto.domain.model.Produto;


/**
 * @author cbgomes
 *
 */
public interface ProdutoService {

	public List<Produto> list();

	public Produto save(Produto produto);

	public Produto getById(Long id);

	public Produto buscaPrimeiroCadastro();

	public void remove(Long id);

}
