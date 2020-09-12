/**
 * 
 */
package br.com.viafood.estado.api;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import br.com.viafood.estado.business.EstadoService;
import br.com.viafood.estado.domain.model.Estado;
import br.com.viafood.estado.domain.repository.EstadoRepository;
import br.com.viafood.restaurante.domain.model.Restaurante;

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
	public final List<Estado> listar() {
		return this.service.list();
	}
	
	@PostMapping("/estados")
	public final ResponseEntity<Estado> save(@RequestBody final Estado estado){
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(estado));
	}
	
	@GetMapping("/estados/{id}")
	public final ResponseEntity<Estado> getById(@PathVariable final Long id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(this.service.getById(id));
		} catch (EntidadeNaoEncontrada e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/estados/{id}")
	public final ResponseEntity<Estado> remove(@PathVariable final Long id) {
		try {
			this.service.remove(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			
		} catch (EntidadeComDependencia e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
			
		}catch (EntidadeNaoEncontrada e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@PutMapping("/estados/{id}")
	public final ResponseEntity<Estado> edit(@PathVariable final Long id, @RequestBody Estado estado){
		Estado estadoBase = this.service.getById(id);
		
		if(estadoBase == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}else {
			BeanUtils.copyProperties(estado, estadoBase, "id");
			this.service.save(estadoBase);
			return ResponseEntity.status(HttpStatus.CREATED).build();			
		}
	}
}
