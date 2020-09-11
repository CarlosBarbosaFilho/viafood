/**
 * 
 */
package br.com.viafood.estado.api.resource;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.viafood.estado.domain.model.Estado;
import br.com.viafood.estado.domain.repository.EstadoRepository;

/**
 * @author cbgomes
 *
 */
@RestController
@RequestMapping(value = "/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public final class EstadoResourceRest {
	
	private final EstadoRepository estadoRespository;

	public EstadoResourceRest(final EstadoRepository estadoRespository) {
		this.estadoRespository = estadoRespository;
	}
	
	@GetMapping("/estados")
	public final List<Estado> listar() {
		return this.estadoRespository.list();
	}
}
