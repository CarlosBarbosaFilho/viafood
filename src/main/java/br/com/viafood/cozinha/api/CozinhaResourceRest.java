/**
 * 
 */
package br.com.viafood.cozinha.api;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.viafood.cozinha.business.CozinhaService;
import br.com.viafood.cozinha.domain.model.Cozinha;

/**
 * @author cbgomes
 *
 */
@RestController
@RequestMapping(value = "/api/v1")
public final class CozinhaResourceRest {

	private final CozinhaService service;

	@Autowired
	public CozinhaResourceRest(final CozinhaService service) {
		this.service = service;
	}

	@GetMapping("/cozinhas")
	@ResponseStatus(value = HttpStatus.OK)
	public final List<Cozinha> list() {
		return this.service.list();
	}

	@PostMapping("/cozinhas")
	@ResponseStatus(value = HttpStatus.CREATED)
	public final Cozinha save(@RequestBody final Cozinha cozinha) {
		return this.service.save(cozinha);
	}

	@PutMapping("/cozinhas/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public final Cozinha edit(@PathVariable final Long id, @RequestBody final Cozinha cozinha) {
		Cozinha cozinhaBase = this.service.getById(id);

		BeanUtils.copyProperties(cozinha, cozinhaBase, "id");
		return this.service.save(cozinhaBase);
	}

	@GetMapping("/cozinhas/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public final Cozinha getById(@PathVariable final Long id) {
		return this.service.getById(id);
	}

	@GetMapping("/cozinhas/nome")
	@ResponseStatus(value = HttpStatus.OK)
	public final Cozinha getByNome(String nome) {
		return this.service.getByNome(nome);
	}

	@GetMapping("/cozinhas/exists")
	@ResponseStatus(value = HttpStatus.OK)
	public final boolean existCozinha(String nome) {
		return this.service.existsCozinha(nome);
	}

	@DeleteMapping("/cozinhas/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public final void remove(@PathVariable final Long id) {
		this.service.remove(id);
	}
}
