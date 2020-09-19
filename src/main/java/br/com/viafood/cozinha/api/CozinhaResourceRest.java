/**
 * 
 */
package br.com.viafood.cozinha.api;

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
import org.springframework.web.bind.annotation.RestController;

import br.com.viafood.cozinha.business.CozinhaService;
import br.com.viafood.cozinha.domain.model.Cozinha;
import br.com.viafood.cozinha.exception.EntidadeComDependencia;
import br.com.viafood.cozinha.exception.EntidadeNaoEncontrada;

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
	public final ResponseEntity<List<Cozinha>> list() {
		List<Cozinha> cozinhas = this.service.list();
		if (cozinhas.isEmpty()) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.ok(cozinhas);
	}

	@PostMapping("/cozinhas")
	public final ResponseEntity<Cozinha> save(@RequestBody final Cozinha cozinha) {
		this.service.save(cozinha);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PutMapping("/cozinhas/{id}")
	public final ResponseEntity<Cozinha> edit(@PathVariable final Long id, @RequestBody final Cozinha cozinha) {
		Cozinha cozinhaBase = this.service.getById(id);

		if (cozinhaBase != null) {
			BeanUtils.copyProperties(cozinha, cozinhaBase, "id");
			this.service.save(cozinhaBase);

			return ResponseEntity.ok(cozinhaBase);
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/cozinhas/{id}")
	public final ResponseEntity<Cozinha> getById(@PathVariable final Long id) {
		Cozinha cozinha = this.service.getById(id);
		if (cozinha == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} else {
			return ResponseEntity.ok(cozinha);
		}
	}
	
	@GetMapping("/cozinhas/nome")
	public final ResponseEntity<Cozinha> getByNome(String nome) {
		Cozinha cozinha = this.service.getByNome(nome);
		if (cozinha == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.ok(cozinha);
		}
	}
	
	@GetMapping("/cozinhas/exists")
	public final boolean existCozinha(String nome) {
		return this.service.existsCozinha(nome);
	}
	
	@DeleteMapping("/cozinhas/{id}")
	public final ResponseEntity<?> remove(@PathVariable final Long id) {
		try {
			this.service.remove(id);
			return ResponseEntity.noContent().build();

		} catch (EntidadeNaoEncontrada e) {
			return ResponseEntity.notFound().build();

		} catch (EntidadeComDependencia e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}

	}
}
