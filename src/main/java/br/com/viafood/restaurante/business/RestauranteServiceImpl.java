/**
 * 
 */
package br.com.viafood.restaurante.business;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.viafood.cozinha.domain.repository.CozinhaRepository;
import br.com.viafood.cozinha.exception.CozinhaNaoEncontradaException;
import br.com.viafood.exceptions.exception.EntidadeComDependencia;
import br.com.viafood.restaurante.domain.model.Restaurante;
import br.com.viafood.restaurante.domain.repository.RestauranteRepository;
import br.com.viafood.restaurante.exception.RestauranteNaoEncotradoExeption;
import br.com.viafood.restaurante.infrastructure.specification.RestauranteSpec;

/**
 * @author cbgomes
 *
 */
@Service
public class RestauranteServiceImpl implements RestauranteService {

	private RestauranteRepository repository;
	private CozinhaRepository cozinhaRepository;

	@Autowired
	public RestauranteServiceImpl(RestauranteRepository repository, CozinhaRepository cozinhaRepository) {
		this.repository = repository;
		this.cozinhaRepository = cozinhaRepository;
	}

	@Override
	public List<Restaurante> list() {
		return this.repository.findAll();
	}

	@Override
	public List<Restaurante> listRestauranteFreteGratis(String nome) {
		return this.repository.findAll(RestauranteSpec.freteGratis().and(RestauranteSpec.nomeSemelhante(nome)));
	}

	@Transactional
	@Override
	public Restaurante save(Restaurante restaurante) {
		restaurante.setCozinha(this.cozinhaRepository.findById(restaurante.getCozinha().getId())
				.orElseThrow(() -> new CozinhaNaoEncontradaException(restaurante.getCozinha().getId())));

		return this.repository.save(restaurante);
	}

	@Override
	public Restaurante getById(Long id) {
		return this.repository.findById(id).orElseThrow(() -> {
			throw new RestauranteNaoEncotradoExeption(id);
		});
	}
	
	@Transactional
	@Override
	public void desativarRestaurante(Long id) {
		this.repository.findById(id).orElseThrow(() ->{
			throw new RestauranteNaoEncotradoExeption(id);
		}).setAtivo(false);
	}

	@Transactional
	@Override
	public void remove(Long id) {
		try {
			this.repository.delete(this.repository.findById(id).orElseThrow(() -> {
				throw new RestauranteNaoEncotradoExeption(id);
			}));
			this.repository.flush();
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeComDependencia(
					String.format("Entidade com %d não pode ser removida, existem dependências vinculdas", id));
		}
	}

	@Override
	public List<Restaurante> listaRestaurantesPorFaixaDeTaxaFrete(BigDecimal taxaInicial, BigDecimal taxaFinal) {
		return this.repository.findByTaxaFreteBetween(taxaInicial, taxaFinal);
	}

	@Override
	public List<Restaurante> listaRestaurantePorNomeECozinha(String nome, Long idCozinha) {
		return this.repository.findByNomeContainingAndCozinhaId(nome, idCozinha);
	}

	@Override
	public int countCozinhas(Long id) {
		return this.repository.countByCozinhaId(id);
	}

	@Override
	public List<Restaurante> recuperaRestaurantePorNomeEFaixaDeFrete(String nome, BigDecimal taxaInicial,
			BigDecimal taxaFinal) {
		return this.repository.getByNomeAndTaxaInicialTaxaFinal(nome, taxaInicial, taxaFinal);
	}

	@Override
	public Restaurante buscaPrimeiroCadastro() {
		Optional<Restaurante> restaurante = this.repository.buscaPrimeiroCadastro();
		return restaurante.get();
	}
}
