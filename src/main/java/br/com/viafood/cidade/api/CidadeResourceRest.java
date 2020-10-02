/**
 * 
 */
package br.com.viafood.cidade.api;

import java.util.List;
import java.util.stream.Collectors;

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
import br.com.viafood.cidade.domain.model.dto.CidadeDto;
import br.com.viafood.cidade.domain.model.form.CidadeForm;
import br.com.viafood.estado.domain.model.Estado;
import br.com.viafood.estado.domain.model.dto.EstadoDto;
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
	public final List<CidadeDto> list() {
		return toListDtos(this.service.list());
	}

	@PostMapping("/cidades")
	@ResponseStatus(value = HttpStatus.CREATED)
	public final CidadeDto save(@RequestBody @Valid final CidadeForm cidadeForm) {
		try {
			return toDto(this.service.save(toCidadeForm(cidadeForm)));
		} catch (EstadoNaoEcontradoException e) {
			throw new BusinessException(e.getMessage(), e);
		}
	}

	@PutMapping("/cidades/{id}")
	@ResponseStatus(value = HttpStatus.CREATED)
	public final CidadeDto edit(@PathVariable final Long id, @RequestBody @Valid final CidadeForm cidadeForm) {

		try {
			Cidade cidadeBase = this.service.getById(id);
			BeanUtils.copyProperties(toCidadeForm(cidadeForm), cidadeBase, "id");
			return toDto(this.service.save(cidadeBase));

		} catch (EstadoNaoEcontradoException e) {
			throw new BusinessException(e.getMessage(), e);
		}
	}

	@GetMapping("/cidades/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public final CidadeDto getById(@PathVariable final Long id) {
		return toDto(this.service.getById(id));
	}

	@DeleteMapping("/cidades/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public final void remove(@PathVariable final Long id) {
		this.service.remove(id);
	}

	private CidadeDto toDto(final Cidade cidade) {
		EstadoDto estadoDto = new EstadoDto();
		estadoDto.setId(cidade.getEstado().getId());

		CidadeDto cidadeDto = new CidadeDto();
		cidadeDto.setId(cidade.getId());
		cidadeDto.setNome(cidade.getNome());
		cidadeDto.setEstado(estadoDto);

		return cidadeDto;
	}

	private Cidade toCidadeForm(CidadeForm form) {
		Estado estado = new Estado();
		estado.setId(form.getEstado().getId());

		Cidade cidade = new Cidade();
		cidade.setNome(form.getNome());
		cidade.setEstado(estado);

		return cidade;

	}
	private List<CidadeDto> toListDtos(List<Cidade> cidades) {
		return cidades.stream().map(cidade -> toDto(cidade)).collect(Collectors.toList());
	}

}
