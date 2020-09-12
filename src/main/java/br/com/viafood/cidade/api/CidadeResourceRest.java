/**
 * 
 */
package br.com.viafood.cidade.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.viafood.cidade.business.CidadeService;
import br.com.viafood.cidade.domain.model.Cidade;
import br.com.viafood.cozinha.exception.EntidadeComDependencia;

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
	public final ResponseEntity<List<Cidade>> list(){
 		return ResponseEntity.status(HttpStatus.OK).body(this.service.list());
	}
	
	@PostMapping("/cidades")
	public final ResponseEntity<Cidade> save(@RequestBody final Cidade cidade){
		try {			
			return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(cidade));
			
		} catch (EntidadeComDependencia e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
}
