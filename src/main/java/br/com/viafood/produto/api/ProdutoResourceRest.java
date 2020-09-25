/**
 * 
 */
package br.com.viafood.produto.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.viafood.produto.business.ProdutoService;
import br.com.viafood.produto.domain.model.Produto;

/**
 * @author cbgomes
 *
 */
@RestController
@RequestMapping(value = "/api/v1")
public class ProdutoResourceRest {

	private final ProdutoService service;
	
	@Autowired
	public ProdutoResourceRest(final ProdutoService service) {
		this.service = service;
	}
	
	@GetMapping("/produtos")
	@ResponseStatus(value = HttpStatus.OK)
	public final List<Produto> listar(){
		return this.service.list();
	}
}
