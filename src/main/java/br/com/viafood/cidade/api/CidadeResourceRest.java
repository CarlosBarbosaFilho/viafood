/**
 * 
 */
package br.com.viafood.cidade.api;

import java.util.List;

import javax.validation.Valid;

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

import br.com.viafood.cidade.business.CidadeService;
import br.com.viafood.cidade.domain.model.Cidade;
import br.com.viafood.estado.exception.EstadoNaoEcontradoException;
import br.com.viafood.exceptions.exception.BusinessException;

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
	public final Cidade save(@RequestBody @Valid final Cidade cidade) {
		try {
			return this.service.save(cidade);			
		} catch (EstadoNaoEcontradoException e) {
			throw new BusinessException(e.getMessage(), e);
		}
	}

	@PutMapping("/cidades/{id}")
	@ResponseStatus(value = HttpStatus.CREATED)
	public final Cidade edit(@PathVariable final Long id, @RequestBody @Valid final Cidade cidade) {
		
		try {
			Cidade cidadeBase = this.service.getById(id);
			BeanUtils.copyProperties(cidade, cidadeBase, "id");
			Cidade cid = this.service.save(cidadeBase);
			this.service.save(cid);			
			return cid;
		} catch (EstadoNaoEcontradoException e) {
			throw new BusinessException(e.getMessage(),e);
		}

	}

	@GetMapping("/cidades/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public final Cidade getById(@PathVariable final Long id) {
		return this.service.getById(id);
	}

	@DeleteMapping("/cidades/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public final void remove(@PathVariable final Long id) {
		 this.service.remove(id);
	}
	
}
