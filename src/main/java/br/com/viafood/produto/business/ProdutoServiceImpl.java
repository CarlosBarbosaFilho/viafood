/**
 * 
 */
package br.com.viafood.produto.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.viafood.produto.domain.model.Produto;
import br.com.viafood.produto.domain.repository.ProdutoRepository;
import br.com.viafood.produto.exception.ProdutoNaoEncontradoException;

/**
 * @author cbgomes
 *
 */
@Service
public class ProdutoServiceImpl implements ProdutoService {

	private ProdutoRepository repository;

	@Autowired
	public ProdutoServiceImpl(ProdutoRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<Produto> list() {
		return this.repository.findAll();
	}

	@Override
	public Produto save(Produto produto) {
		return this.repository.save(produto);
	}

	@Override
	public Produto getById(Long id) {
		return this.repository.findById(id).orElseThrow(()->{
			throw new ProdutoNaoEncontradoException(id);
		});
	}

	@Override
	public Produto buscaPrimeiroCadastro() {
		return this.repository.buscaPrimeiroCadastro().get();
	}

	@Override
	public void remove(Long id) {
		this.repository.delete(this.repository.findById(id).orElseThrow(() -> {
			throw new ProdutoNaoEncontradoException(id);
		}));
	}

}
