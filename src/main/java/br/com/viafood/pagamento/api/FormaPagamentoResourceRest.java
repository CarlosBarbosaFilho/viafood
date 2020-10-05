/**
 * 
 */
package br.com.viafood.pagamento.api;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.viafood.exceptions.exception.EntidadeNaoEncontradaException;
import br.com.viafood.pagamento.business.FormasPagamentosService;
import br.com.viafood.pagamento.domain.model.FormaPagamento;
import br.com.viafood.pagamento.domain.model.conveter.FormaPagamentoConverterDTO;
import br.com.viafood.pagamento.domain.model.conveter.FormaPagamentoConverterForm;
import br.com.viafood.pagamento.domain.model.dto.FormaPagamentoDto;
import br.com.viafood.pagamento.domain.model.form.FormaPagamentoForm;
import br.com.viafood.pagamento.exception.FormaPagamentoNaoEncontradaException;

/**
 * @author david
 *
 */
@RestController
@RequestMapping(value = "/api/v1")
public class FormaPagamentoResourceRest {

	private FormasPagamentosService service;

	private FormaPagamentoConverterDTO formaPagamentoConverterDTO;

	private FormaPagamentoConverterForm formaPagamentoConverterForm;

	@Autowired
	public FormaPagamentoResourceRest(final FormasPagamentosService service,
			final FormaPagamentoConverterDTO formaPagamentoConverterDTO,
			final FormaPagamentoConverterForm formaPagamentoConverterForm) {

		this.service = service;
		this.formaPagamentoConverterDTO = formaPagamentoConverterDTO;
		this.formaPagamentoConverterForm = formaPagamentoConverterForm;
	}

	@GetMapping("/formaspagamentos")
	@ResponseStatus(value = HttpStatus.OK)
	public List<FormaPagamentoDto> list() {
		return this.formaPagamentoConverterDTO.formasPagamentosDtos(this.service.list());
	}

	@PostMapping("/formaspagamentos")
	@ResponseStatus(value = HttpStatus.CREATED)
	public FormaPagamentoDto save(@RequestBody final FormaPagamentoForm formaPagamentoForm) {
		return this.formaPagamentoConverterDTO.ToDto(this.service
				.save(this.formaPagamentoConverterForm.ToFormaPagamentoFormFormaPagamento(formaPagamentoForm)));
	}

	@GetMapping("/formaspagamentos/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public FormaPagamentoDto getFormaPagamento(@PathVariable final Long id) {
		return this.formaPagamentoConverterDTO.ToDto(this.service.getById(id));
	}

	@PutMapping("/formaspagamentos/{id}")
	@ResponseStatus(value = HttpStatus.CREATED)
	public FormaPagamentoDto edit(@PathVariable final Long id,
			@RequestBody final FormaPagamentoForm formaPagamentoForm) {
		try {
			FormaPagamento formaPagamentoAtual = this.service.getById(id);
			this.formaPagamentoConverterForm.copyFormaPagamentoFormToFormaPagamento(formaPagamentoForm,
					formaPagamentoAtual);
			return this.formaPagamentoConverterDTO.ToDto(this.service.save(formaPagamentoAtual));
		} catch (EntidadeNaoEncontradaException e) {
			throw new FormaPagamentoNaoEncontradaException(e.getMessage());
		}
	}

	@DeleteMapping("/formaspagamentos/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void remove(@PathVariable final Long id) {
		this.service.remove(id);
	}
}
