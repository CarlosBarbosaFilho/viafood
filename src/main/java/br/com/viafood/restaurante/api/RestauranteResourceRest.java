/**
 * 
 */
package br.com.viafood.restaurante.api;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.viafood.cozinha.exception.EntidadeComDependencia;
import br.com.viafood.cozinha.exception.EntidadeNaoEncontradaException;
import br.com.viafood.restaurante.business.RestauranteService;
import br.com.viafood.restaurante.domain.model.Restaurante;

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
	public final ResponseEntity<List<Restaurante>> list() {
		return ResponseEntity.ok(this.service.list());
	}

	@PostMapping("/restaurantes")
	public final ResponseEntity<?> save(@RequestBody final Restaurante restaurante) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(restaurante));
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("/restaurantes/{id}")
	public final ResponseEntity<?> edit(@PathVariable final Long id, @RequestBody final Restaurante restaurante) {
		Restaurante restauranteBase = this.service.getById(id);
		if (restauranteBase != null) {
			BeanUtils.copyProperties(restaurante, restauranteBase, "id","formasPagamentos","endereco", "dataCadastro");
			Restaurante rt = this.service.save(restauranteBase);
			return ResponseEntity.ok(rt);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@PatchMapping("/restaurantes/{id}")
	public final ResponseEntity<?> editPart(@PathVariable final Long id,
			@RequestBody final Map<String, Object> fieldsRequest) {

		Restaurante restaurante = this.service.getById(id);
		if (restaurante == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		mergePath(fieldsRequest, restaurante);

		return edit(id, restaurante);
	}

	private void mergePath(final Map<String, Object> fieldsResquest, Restaurante restauranteUpdated) {

		ObjectMapper objectMapper = new ObjectMapper();
		// INSTANCIA CRIADA PRA EVITAR A CONVERSÃO DOS VALORES NA ATUALIZAÇÃO DE CAMPOS
		// DISTINTOS
		Restaurante restauranteCreatedWithFieldsRequest = objectMapper.convertValue(fieldsResquest, Restaurante.class);

		fieldsResquest.forEach((nomePropriedade, valorPropriedade) -> {
			Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
			field.setAccessible(true);

			Object newVelueField = ReflectionUtils.getField(field, restauranteCreatedWithFieldsRequest);

			ReflectionUtils.setField(field, restauranteUpdated, newVelueField);
		});
	}

	@GetMapping("/restaurantes/{id}")
	public final ResponseEntity<Restaurante> getById(@PathVariable final Long id) {
		Restaurante restaurante = this.service.getById(id);
		if (restaurante == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(restaurante);
		}
	}
	
	@GetMapping("/restaurantes/first")
	public final ResponseEntity<Restaurante> getFirstRestaurante(){
		return ResponseEntity.ok(this.service.buscaPrimeiroCadastro());
	}

	@GetMapping("/restaurantes/taxa-frete")
	public final ResponseEntity<List<Restaurante>> listRestauranteByBetWeenTaxas( BigDecimal taxa_inicial , BigDecimal taxa_final ){
		List<Restaurante> restaurantes = 
		 this.service.listaRestaurantesPorFaixaDeTaxaFrete(taxa_inicial, taxa_final);
		return ResponseEntity.ok(restaurantes);
	}
	
	@GetMapping("/restaurantes/nome-taxa-frete")
	public final ResponseEntity<List<Restaurante>> listRestaurantePorNomeETaxaFrte(String nome, BigDecimal taxa_inicial , BigDecimal taxa_final ){
		List<Restaurante> restaurantes = 
		 this.service.recuperaRestaurantePorNomeEFaixaDeFrete(nome, taxa_inicial, taxa_final);
		return ResponseEntity.ok(restaurantes);
	}
	
	@GetMapping("/restaurantes/nome-cozinha")
	public final ResponseEntity<List<Restaurante>> listaRestaurantePorNomeEIdCozinha(String nome, Long idCozinha){
		List<Restaurante> restaurantes = 
				this.service.listaRestaurantePorNomeECozinha(nome, idCozinha);
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

		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

		} catch (EntidadeComDependencia e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
}
