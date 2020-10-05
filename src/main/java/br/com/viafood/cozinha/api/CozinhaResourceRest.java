/**
 * 
 */
package br.com.viafood.cozinha.api;

import java.util.List;

import javax.validation.Valid;

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
import br.com.viafood.cozinha.domain.model.converter.CozinhaConverterDTO;
import br.com.viafood.cozinha.domain.model.converter.CozinhaConverterForm;
import br.com.viafood.cozinha.domain.model.dto.CozinhaDto;
import br.com.viafood.cozinha.domain.model.form.CozinhaForm;
import br.com.viafood.cozinha.exception.CozinhaNaoEncontradaException;
import br.com.viafood.exceptions.exception.BusinessException;

/**
 * @author cbgomes
 *
 */
@RestController
@RequestMapping(value = "/api/v1")
public final class CozinhaResourceRest {

	private final CozinhaService service;
	
	private CozinhaConverterDTO cozinhaConverterDto;
	
	private CozinhaConverterForm cozinhaConverterForm;

	@Autowired
	public CozinhaResourceRest(final CozinhaService service, final CozinhaConverterDTO cozinhaConverterDto,
			final CozinhaConverterForm cozinhaConverterForm) {
		this.service = service;
		this.cozinhaConverterDto = cozinhaConverterDto;
		this.cozinhaConverterForm = cozinhaConverterForm;
	}

	@GetMapping("/cozinhas")
	@ResponseStatus(value = HttpStatus.OK)
	public final List<CozinhaDto> list() {
		return this.cozinhaConverterDto.cozinhasDtos(this.service.list());
	}

	@PostMapping("/cozinhas")
	@ResponseStatus(value = HttpStatus.CREATED)
	public final CozinhaDto save(@RequestBody final @Valid Cozinha cozinha) {
		return this.cozinhaConverterDto.ToDto(this.service.save(cozinha));
	}

	@PutMapping("/cozinhas/{id}")
	@ResponseStatus(value = HttpStatus.CREATED)
	public final CozinhaDto edit(@PathVariable final Long id, @RequestBody final CozinhaForm cozinhaForm) {
		
		try {			
			Cozinha cozinhaBase = this.service.getById(id);
			this.cozinhaConverterForm.copyCozinhaFormToCozinha(cozinhaForm, cozinhaBase);
			return this.cozinhaConverterDto.ToDto(this.service.save(cozinhaBase));
			
		} catch (CozinhaNaoEncontradaException e) {
			throw new BusinessException(e.getMessage());
		}
	}

	@GetMapping("/cozinhas/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public final CozinhaDto getById(@PathVariable final Long id) {
		return this.cozinhaConverterDto.ToDto(this.service.getById(id));
	}

	@GetMapping("/cozinhas/nome")
	@ResponseStatus(value = HttpStatus.OK)
	public final CozinhaDto getByNome(String nome) {
		return this.cozinhaConverterDto.ToDto(this.service.getByNome(nome));
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
