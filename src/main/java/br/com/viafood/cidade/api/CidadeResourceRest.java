/**
 * 
 */
package br.com.viafood.cidade.api;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.viafood.cidade.business.CidadeService;
import br.com.viafood.cidade.domain.model.Cidade;
import br.com.viafood.cozinha.exception.EntidadeNaoEncontradaException;

/**
 * @author carlosfilho
 *
 */
@RestController
@RequestMapping("/api/v1")
public class CidadeResourceRest {

	private final CidadeService service;

	@Autowired
	public CidadeResourceRest(final CidadeService service) {
		this.service = service;
	}

	@GetMapping("/cidades")
	@ResponseStatus(value = HttpStatus.OK)
	public final List<Cidade> list() {
		return this.service.list();
	}

	@PostMapping("/cidades")
	@ResponseStatus(value = HttpStatus.CREATED)
	public final Cidade save(@RequestBody final Cidade cidade) {
		return this.service.save(cidade);
	}

	@PutMapping("/cidades/{id}")
	@ResponseStatus(value = HttpStatus.CREATED)
	public final Cidade edit(@PathVariable final Long id, @RequestBody final Cidade cidade) {
		Cidade cidadeBase = this.service.getById(id);
		
		BeanUtils.copyProperties(cidade, cidadeBase, "id");
		this.service.save(cidadeBase);

		return cidadeBase;
	}

	@GetMapping("/cidades/{id}")

	public final Cidade getById(@PathVariable final Long id) {
		return this.service.getById(id);
	}

	@DeleteMapping("/cidades/{id}")
	public final ResponseEntity<Cidade> remove(@PathVariable final Long id) {
		try {
			this.service.remove(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
}
