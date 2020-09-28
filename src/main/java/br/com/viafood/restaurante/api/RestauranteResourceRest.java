/**
 * 
 */
package br.com.viafood.restaurante.api;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.viafood.cozinha.exception.CozinhaNaoEncontradaException;
import br.com.viafood.exceptions.exception.BusinessException;
import br.com.viafood.exceptions.exception.EntidadeComDependencia;
import br.com.viafood.restaurante.business.RestauranteService;
import br.com.viafood.restaurante.domain.model.Restaurante;
import br.com.viafood.restaurante.exception.RestauranteNaoEncotradoExeption;

/**
 * @author cbgomes
 *
 */
@RestController
@RequestMapping("/api/v1")
public class RestauranteResourceRest {

	private final RestauranteService service;

	@Autowired
	public RestauranteResourceRest(RestauranteService service) {
		this.service = service;
	}

	@GetMapping("/restaurantes")
	@ResponseStatus(value = HttpStatus.OK)
	public final List<Restaurante> list() {
		return this.service.list();
	}

	@PostMapping("/restaurantes")
	@ResponseStatus(value = HttpStatus.CREATED)
	public final Restaurante save(@RequestBody final @Valid Restaurante restaurante) {
		try {
			return this.service.save(restaurante);			
		} catch (CozinhaNaoEncontradaException e) {
			throw new BusinessException(e.getMessage());
		}
	}

	@PutMapping("/restaurantes/{id}")
	@ResponseStatus(value = HttpStatus.CREATED)
	public final Restaurante edit(@PathVariable final Long id, @RequestBody @Valid final Restaurante restaurante) {
		Restaurante restauranteBase = this.service.getById(id);
		BeanUtils.copyProperties(restaurante, restauranteBase, "id", "formasPagamentos", "endereco", "dataCadastro");

		try {
			Restaurante rt = this.service.save(restauranteBase);
			return rt;
		} catch (CozinhaNaoEncontradaException e) {
			throw new BusinessException(e.getMessage());
		}
	}

	@PatchMapping("/restaurantes/{id}")
	@ResponseStatus(value = HttpStatus.CREATED)
	public final Restaurante editPart(@PathVariable final Long id, @RequestBody final Map<String, Object> fieldsRequest,
			HttpServletRequest request) {
		
		Restaurante restaurante = this.service.getById(id);
		mergePath(fieldsRequest, restaurante, request);
		return edit(id, restaurante);

	}

	private void mergePath(final Map<String, Object> fieldsResquest, Restaurante restauranteUpdated,
			HttpServletRequest request) {

		ServletServerHttpRequest serverHttpRequest = new ServletServerHttpRequest(request);

		try {
			ObjectMapper objectMapper = new ObjectMapper();
			Restaurante restauranteCreatedWithFieldsRequest = objectMapper.convertValue(fieldsResquest,
					Restaurante.class);

			fieldsResquest.forEach((nomePropriedade, valorPropriedade) -> {
				Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
				field.setAccessible(true);

				Object newVelueField = ReflectionUtils.getField(field, restauranteCreatedWithFieldsRequest);

				ReflectionUtils.setField(field, restauranteUpdated, newVelueField);
			});
		} catch (IllegalArgumentException e) {
			Throwable rootCause = ExceptionUtils.getRootCause(e);
			throw new HttpMessageNotReadableException(e.getMessage(), rootCause, serverHttpRequest);
		}
	}

	@GetMapping("/restaurantes/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public final Restaurante getById(@PathVariable final Long id) {
		return this.service.getById(id);
	}

	@GetMapping("/restaurantes/first")
	public final ResponseEntity<Restaurante> getFirstRestaurante() {
		return ResponseEntity.ok(this.service.buscaPrimeiroCadastro());
	}

	@GetMapping("/restaurantes/taxa-frete")
	@ResponseStatus(value = HttpStatus.OK)
	public final List<Restaurante> listRestauranteByBetWeenTaxas(BigDecimal taxa_inicial, BigDecimal taxa_final) {
		List<Restaurante> restaurantes = this.service.listaRestaurantesPorFaixaDeTaxaFrete(taxa_inicial, taxa_final);
		return restaurantes;
	}

	@GetMapping("/restaurantes/nome-taxa-frete")
	public final ResponseEntity<List<Restaurante>> listRestaurantePorNomeETaxaFrte(String nome, BigDecimal taxa_inicial,
			BigDecimal taxa_final) {
		List<Restaurante> restaurantes = this.service.recuperaRestaurantePorNomeEFaixaDeFrete(nome, taxa_inicial,
				taxa_final);
		return ResponseEntity.ok(restaurantes);
	}

	@GetMapping("/restaurantes/nome-cozinha")
	public final ResponseEntity<List<Restaurante>> listaRestaurantePorNomeEIdCozinha(String nome, Long idCozinha) {
		List<Restaurante> restaurantes = this.service.listaRestaurantePorNomeECozinha(nome, idCozinha);
		return ResponseEntity.ok(restaurantes);
	}

	@GetMapping("/restaurantes/count")
	public final int countRestaurantesCozinhas(Long idCozinha) {
		return this.service.countCozinhas(idCozinha);
	}

	@GetMapping("/restaurantes/frete-free")
	public final List<Restaurante> restaurantesDeliveryFree(String nome) {
		return this.service.listRestauranteFreteGratis(nome);
	}

	@DeleteMapping("/restaurantes/{id}")
	public final ResponseEntity<Restaurante> remove(@PathVariable final Long id) {
		try {
			this.service.remove(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

		} catch (RestauranteNaoEncotradoExeption e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

		} catch (EntidadeComDependencia e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
}
