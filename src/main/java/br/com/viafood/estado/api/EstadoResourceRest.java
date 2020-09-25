/**
 * 
 */
package br.com.viafood.estado.api;

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

import br.com.viafood.estado.business.EstadoService;
import br.com.viafood.estado.domain.model.Estado;

/**
 * @author cbgomes
 *
 */
@RestController
@RequestMapping(value = "/api/v1")
public final class EstadoResourceRest {

	private final EstadoService service;

	@Autowired
	public EstadoResourceRest(final EstadoService service) {
		this.service = service;
	}

	@GetMapping("/estados")
	@ResponseStatus(value = HttpStatus.OK)
	public final List<Estado> listar() {
		return this.service.list();
	}

	@PostMapping("/estados")
	@ResponseStatus(value = HttpStatus.CREATED)
	public final Estado save(@RequestBody final Estado estado) {
		return this.service.save(estado);
	}

	@PutMapping("/estados/{id}")
	@ResponseStatus(value = HttpStatus.CREATED)
	public final Estado edit(@PathVariable final Long id, @RequestBody Estado estado) {
		Estado estadoBase = this.service.getById(id);
		BeanUtils.copyProperties(estado, estadoBase, "id");
		this.service.save(estadoBase);

		return estadoBase;
	}

	@GetMapping("/estados/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public final Estado getById(@PathVariable final Long id) {
		return this.service.getById(id);
	}

	@DeleteMapping("/estados/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public final void remove(@PathVariable final Long id) {
			this.service.remove(id);
	}

}
