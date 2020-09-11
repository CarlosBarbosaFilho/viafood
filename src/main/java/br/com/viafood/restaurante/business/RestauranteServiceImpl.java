/**
 * 
 */
package br.com.viafood.restaurante.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

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
	
	@Autowired
	public RestauranteServiceImpl(RestauranteRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<Restaurante> list() {
		return this.repository.list();
	}

	@Override
	public void save(Restaurante restaurante) {
		this.repository.save(restaurante);
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

}
