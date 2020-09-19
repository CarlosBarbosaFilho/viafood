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
import org.springframework.web.bind.annotation.RestController;

import br.com.viafood.cidade.business.CidadeService;
import br.com.viafood.cidade.domain.model.Cidade;
import br.com.viafood.cozinha.exception.EntidadeNaoEncontrada;

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
	public final ResponseEntity<List<Cidade>> list() {
		return ResponseEntity.status(HttpStatus.OK).body(this.service.list());
	}

	@PostMapping("/cidades")
	public final ResponseEntity<?> save(@RequestBody final Cidade cidade) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(cidade));

		} catch (EntidadeNaoEncontrada e) {
			return ResponseEntity.badRequest().body("Estado não pode ser nulo");
		}
	}
	
	@PutMapping("/cidades/{id}")
	public final ResponseEntity<?> edit(@PathVariable final Long id, @RequestBody final Cidade cidade){
		try {			
			Cidade cidadeBase = this.service.getById(id);
			if(cidadeBase != null ) {
				BeanUtils.copyProperties(cidade, cidadeBase, "id");
				cidadeBase = this.service.save(cidadeBase);
				return ResponseEntity.status(HttpStatus.CREATED).body(cidadeBase);
			}
			return ResponseEntity.notFound().build();
			
		}catch (EntidadeNaoEncontrada e) {
			return ResponseEntity.badRequest().body("Cidade não localizada ou não existe");
		}
	}

	@GetMapping("/cidades/{id}")
	public final ResponseEntity<Cidade> getById(@PathVariable final Long id){
		Cidade cidade = this.service.getById(id);
		if (cidade == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} else {
			return ResponseEntity.ok(cidade);
		}
	}
	
	@DeleteMapping("/cidades/{id}")
	public final ResponseEntity<Cidade> remove(@PathVariable final Long id) {
		try {
			this.service.remove(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}catch (EntidadeNaoEncontrada e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
}
