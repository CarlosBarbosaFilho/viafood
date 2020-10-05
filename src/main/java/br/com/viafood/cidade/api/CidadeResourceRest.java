/**
 * 
 */
package br.com.viafood.cidade.api;

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

import br.com.viafood.cidade.business.CidadeService;
import br.com.viafood.cidade.domain.model.Cidade;
import br.com.viafood.cidade.domain.model.converter.CidadeConverterDTO;
import br.com.viafood.cidade.domain.model.converter.CidadeConverterForm;
import br.com.viafood.cidade.domain.model.dto.CidadeDto;
import br.com.viafood.cidade.domain.model.form.CidadeForm;
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
	
	private final CidadeConverterDTO cidadeConverterDot;
	
	private final CidadeConverterForm cidadeConverterForm; 

	@Autowired
	public CidadeResourceRest(final CidadeService service, final CidadeConverterDTO cidadeConverterDot,
			final CidadeConverterForm cidadeConverterForm) {
		this.service = service;
		this.cidadeConverterDot = cidadeConverterDot;
		this.cidadeConverterForm = cidadeConverterForm;
	}

	@GetMapping("/cidades")
	@ResponseStatus(value = HttpStatus.OK)
	public final List<CidadeDto> list() {
		return this.cidadeConverterDot.cidadeDtos(this.service.list());
	}

	@PostMapping("/cidades")
	@ResponseStatus(value = HttpStatus.CREATED)
	public final CidadeDto save(@RequestBody @Valid final CidadeForm cidadeForm) {
		try {
			Cidade cidade = this.service.save(this.cidadeConverterForm.ToCidadeForm(cidadeForm));
			return this.cidadeConverterDot.ToDto(cidade);
		} catch (EstadoNaoEcontradoException e) {
			throw new BusinessException(e.getMessage(), e);
		}
	}

	@PutMapping("/cidades/{id}")
	@ResponseStatus(value = HttpStatus.CREATED)
	public final CidadeDto edit(@PathVariable final Long id, @RequestBody @Valid final CidadeForm cidadeForm) {

		try {
			Cidade cidadeAtual = this.service.getById(id);
			this.cidadeConverterForm.copyCidadeFormToCidade(cidadeForm, cidadeAtual);
			return this.cidadeConverterDot.ToDto(this.service.save(cidadeAtual));

		} catch (EstadoNaoEcontradoException e) {
			throw new BusinessException(e.getMessage(), e);
		}
	}

	@GetMapping("/cidades/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public final CidadeDto getById(@PathVariable final Long id) {
		return this.cidadeConverterDot.ToDto(this.service.getById(id));
	}

	@DeleteMapping("/cidades/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public final void remove(@PathVariable final Long id) {
		this.service.remove(id);
	}
}
