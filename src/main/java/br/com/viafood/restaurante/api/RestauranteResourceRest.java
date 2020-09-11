/**
 * 
 */
package br.com.viafood.restaurante.api;

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

import br.com.viafood.cozinha.exception.EntidadeComDependencia;
import br.com.viafood.cozinha.exception.EntidadeNaoEncontrada;
import br.com.viafood.restaurante.business.RestauranteService;
import br.com.viafood.restaurante.domain.model.Restaurante;

/**
 * @author cbgomes
 *
 */
@RestController
@RequestMapping("/api/v1")
public class RestauranteResourceRest {

	private final RestauranteService service;

	@Autowired
	public RestauranteResourceRest(RestauranteService service) {
		this.service = service;
	}

	@GetMapping("/restaurantes")
	public final ResponseEntity<List<Restaurante>> list() {
		return ResponseEntity.ok(this.service.list());
	}

	@PostMapping("/restaurantes")
	public final ResponseEntity<Restaurante> save(@RequestBody final Restaurante restaurante) {
		this.service.save(restaurante);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@GetMapping("/restaurantes/{id}")
	public final ResponseEntity<Restaurante> getRestauranteById(@PathVariable final Long id) {
		Restaurante restaurante = this.service.getById(id);
		if(restaurante == null) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok(restaurante);
		}
	}
	
	@PutMapping("/restaurantes/{id}")
	public final ResponseEntity<Restaurante> edit(@PathVariable final Long id, @RequestBody final Restaurante restaurante){
		Restaurante restauranteBase = this.service.getById(id);
		if(restauranteBase == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}else {
			BeanUtils.copyProperties(restaurante, restauranteBase, "id");
			this.service.save(restaurante);
			return ResponseEntity.status(HttpStatus.CREATED).build();			
		}
	}
	
	@DeleteMapping("/restaurantes/{id}")
	public final ResponseEntity<Restaurante> remove(@PathVariable final Long id) {
		try {
			this.service.remove(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			
		} catch (EntidadeNaoEncontrada e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			
		} catch (EntidadeComDependencia e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
}
