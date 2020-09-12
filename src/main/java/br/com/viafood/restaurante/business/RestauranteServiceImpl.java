/**
 * 
 */
package br.com.viafood.restaurante.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.viafood.cozinha.domain.model.Cozinha;
import br.com.viafood.cozinha.domain.repository.CozinhaRepository;
import br.com.viafood.cozinha.exception.EntidadeComDependencia;
import br.com.viafood.cozinha.exception.EntidadeNaoEncontrada;
import br.com.viafood.restaurante.domain.model.Restaurante;
import br.com.viafood.restaurante.domain.repository.RestauranteRepository;

/**
 * @author cbgomes
 *
 */
@Service
public class RestauranteServiceImpl implements RestauranteService {
	
	private RestauranteRepository repository;
	private CozinhaRepository cozinhaRepository;
	
	@Autowired
	public RestauranteServiceImpl(RestauranteRepository repository,CozinhaRepository cozinhaRepository ) {
		this.repository = repository;
		this.cozinhaRepository = cozinhaRepository;
	}

	@Override
	public List<Restaurante> list() {
		return this.repository.list();
	}

	@Override
	public Restaurante save(Restaurante restaurante) {
		if(existsCozinha(restaurante.getCozinha().getId())) {
			 return this.repository.save(restaurante);			
		}else {
			throw new EntidadeNaoEncontrada(String.format("Cozinha com id %d não foi localizada ou não existe", restaurante.getCozinha().getId()));
		}
	}

	@Override
	public Restaurante getById(Long id) {
		Restaurante restaurante = this.repository.getById(id);
		if(restaurante == null) {
			throw new EntidadeNaoEncontrada(String.format("Entidade restaurante com %d não pode ser localizada ou não existe", id));
		}
		return restaurante;
	}

	@Override
	public void remove(Long id) {
		try {
			this.repository.remove(id);			
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontrada(String.format("Entidade restaurante com %d não localizada ou não existe", id));
		} catch ( DataIntegrityViolationException e) {
			throw new EntidadeComDependencia(String.format("Entidade com %d não pode ser removida, existem dependências vinculdas", id));
		}
	}

	private boolean existsCozinha(Long idCozinha) {
		Cozinha cozinha = this.cozinhaRepository.getById(idCozinha);
		if(cozinha != null ) return true;
		else {
			return false;
		}
	}
}
