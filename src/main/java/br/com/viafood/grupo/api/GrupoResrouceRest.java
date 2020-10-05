/**
 * 
 */
package br.com.viafood.grupo.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.viafood.exceptions.exception.EntidadeNaoEncontradaException;
import br.com.viafood.grupo.business.GrupoService;
import br.com.viafood.grupo.domain.model.Grupo;
import br.com.viafood.grupo.domain.model.converter.GrupoConverterDTO;
import br.com.viafood.grupo.domain.model.converter.GrupoConverterForm;
import br.com.viafood.grupo.domain.model.dto.GrupoDto;
import br.com.viafood.grupo.domain.model.form.GrupoForm;
import br.com.viafood.grupo.exception.GrupoNaoEncontradoException;

/**
 * @author david
 *
 */
@RestController
@RequestMapping("/api/v1")
public class GrupoResrouceRest {

	private final GrupoService service;
	
	private final GrupoConverterDTO grupoConverterDto;
	
	private final GrupoConverterForm grupoConverterForm; 
	
	@Autowired
	public GrupoResrouceRest(final GrupoService service,
			final GrupoConverterDTO grupoConverterDto, final GrupoConverterForm grupoConverterForm) {
		this.service = service;
		this.grupoConverterDto = grupoConverterDto;
		this.grupoConverterForm = grupoConverterForm;
	}
	
	@GetMapping("/grupos")
	@ResponseStatus(value = HttpStatus.OK)
	public List<GrupoDto> list(){
		return this.grupoConverterDto.cozinhasDtos(this.service.list());
	}
	
	@PostMapping("/grupos")
	@ResponseStatus(value = HttpStatus.CREATED)
	public GrupoDto save(@RequestBody final GrupoForm grupoForm) {
		return this.grupoConverterDto
				.ToDto(this.service.save(this.grupoConverterForm.ToGrupoForm(grupoForm)));
	}
	
	@PutMapping("/grupos/{id}")
	@ResponseStatus(value = HttpStatus.CREATED)
	public GrupoDto edit(@PathVariable final Long id, @RequestBody final GrupoForm grupoForm) {
		try {
			Grupo grupoAtual = this.service.getById(id);
			this.grupoConverterForm.copyGrupoFormToGrupo(grupoForm, grupoAtual);
			return this.grupoConverterDto.ToDto(this.service.save(grupoAtual));
		} catch (EntidadeNaoEncontradaException e) {
			throw new GrupoNaoEncontradoException(e.getMessage());
		}
	}
	
	@DeleteMapping("/grupos/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void remove(@PathVariable final Long id) {
		this.service.remove(id);
	}
	
	@GetMapping("/grupos/descrico")
	@ResponseStatus(value = HttpStatus.OK)
	public GrupoDto getByDescricao(final String nome) {
		return this.grupoConverterDto.ToDto(this.service.getByDescricao(nome));
	}
}
