/**
 * 
 */
package br.com.viafood.restaurante.api;

import java.math.BigDecimal;
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

import br.com.viafood.cidade.exception.CidadeNaoEncontradaException;
import br.com.viafood.cozinha.exception.CozinhaNaoEncontradaException;
import br.com.viafood.exceptions.exception.BusinessException;
import br.com.viafood.restaurante.business.RestauranteService;
import br.com.viafood.restaurante.domain.model.Restaurante;
import br.com.viafood.restaurante.domain.model.converter.RestauranteConverterDTO;
import br.com.viafood.restaurante.domain.model.converter.RestauranteConverterForm;
import br.com.viafood.restaurante.domain.model.dto.RestauranteDto;
import br.com.viafood.restaurante.domain.model.form.RestauranteForm;

/**
 * @author cbgomes
 *
 */
@RestController
@RequestMapping("/api/v1")
public class RestauranteResourceRest {

	private final RestauranteService service;
	
	private final RestauranteConverterDTO restauranteConverterDto;
	
	private final RestauranteConverterForm restauranteConverterForm;

	@Autowired
	public RestauranteResourceRest(RestauranteService service, RestauranteConverterDTO restauranteConverterDto
			,RestauranteConverterForm restauranteConverterForm) {
		this.service = service;
		this.restauranteConverterDto = restauranteConverterDto;
		this.restauranteConverterForm = restauranteConverterForm;
	}

	@GetMapping("/restaurantes")
	@ResponseStatus(value = HttpStatus.OK)
	public final List<RestauranteDto> list() {
		return this.restauranteConverterDto.restaurantesDtos(this.service.list());
	}

	@PostMapping("/restaurantes")
	@ResponseStatus(value = HttpStatus.CREATED)
	public final RestauranteDto save(@RequestBody @Valid RestauranteForm restauranteForm) {
		try {
			
			Restaurante restaurante = this.restauranteConverterForm.ToRestauranteForm(restauranteForm);
			restaurante = this.service.save(restaurante);
			return this.restauranteConverterDto.ToDto(restaurante);
			
		} catch (CozinhaNaoEncontradaException e) {
			throw new BusinessException(e.getMessage());
		}
	}
	
	@PutMapping("/restaurantes/{id}")
	@ResponseStatus(value = HttpStatus.CREATED)
	public final RestauranteDto edit(@PathVariable final Long id, @RequestBody final @Valid RestauranteForm restauranteForm) {
		try {
			Restaurante restauranteAtual = this.service.getById(id);
			this.restauranteConverterForm.copyRestauranteFormToRestaurante(restauranteForm, restauranteAtual);
			return this.restauranteConverterDto.ToDto(this.service.save(restauranteAtual));
			
		} catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
			throw new BusinessException(e.getMessage());
		}
	}

	@GetMapping("/restaurantes/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public final RestauranteDto getById(@PathVariable final Long id) {
		return this.restauranteConverterDto.ToDto(this.service.getById(id));
	}
	
	@DeleteMapping("/restaurantes/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public final void remove(@PathVariable final Long id) {
		this.service.remove(id);
	}
	
	@GetMapping("/restaurantes/desativar/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void desativarRestaurante(@PathVariable final Long id) {
		this.service.desativarRestaurante(id);
	}
	
	@GetMapping("/restaurantes/ativar/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void ativarRestaurante(@PathVariable final Long id) {
		this.service.ativarRestaurante(id);
	}

	@GetMapping("/restaurantes/first")
	@ResponseStatus(value = HttpStatus.OK)
	public final RestauranteDto getFirstRestaurante() {
		return this.restauranteConverterDto.ToDto(this.service.buscaPrimeiroCadastro());
	}

	@GetMapping("/restaurantes/taxa-frete")
	@ResponseStatus(value = HttpStatus.OK)
	public final List<RestauranteDto> listRestauranteByBetWeenTaxas(final BigDecimal taxa_inicial, final BigDecimal taxa_final) {
		return this.restauranteConverterDto.restaurantesDtos(this.service.listaRestaurantesPorFaixaDeTaxaFrete(taxa_inicial, taxa_final));
	}

	@GetMapping("/restaurantes/nome-taxa-frete")
	@ResponseStatus(value = HttpStatus.OK)
	public final List<RestauranteDto> listRestaurantePorNomeETaxaFrte(final String nome, final BigDecimal taxa_inicial,
			BigDecimal taxa_final) {
		List<Restaurante> restaurantes = this.service.recuperaRestaurantePorNomeEFaixaDeFrete(nome, taxa_inicial,taxa_final);
		List<RestauranteDto> restaurantesDtos = this.restauranteConverterDto.restaurantesDtos(restaurantes);
		return restaurantesDtos;
	}

	@GetMapping("/restaurantes/nome-cozinha")
	@ResponseStatus(value = HttpStatus.OK)
	public final List<RestauranteDto> listaRestaurantePorNomeEIdCozinha(final String nome, final Long idCozinha) {
		return this.restauranteConverterDto.restaurantesDtos(this.service.listaRestaurantePorNomeECozinha(nome, idCozinha));
	}

	@GetMapping("/restaurantes/count")
	@ResponseStatus(value = HttpStatus.OK)
	public final int countRestaurantesCozinhas(Long idCozinha) {
		return this.service.countCozinhas(idCozinha);
	}

	@GetMapping("/restaurantes/frete-free")
	@ResponseStatus(value = HttpStatus.OK)
	public final List<RestauranteDto> restaurantesDeliveryFree(final String nome) {
		return this.restauranteConverterDto.restaurantesDtos(this.service.listRestauranteFreteGratis(nome));
	}



}
