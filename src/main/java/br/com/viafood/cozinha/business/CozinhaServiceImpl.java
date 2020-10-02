/**
 * 
 */
package br.com.viafood.cozinha.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.viafood.cozinha.domain.model.Cozinha;
import br.com.viafood.cozinha.domain.repository.CozinhaRepository;
import br.com.viafood.cozinha.exception.CozinhaNaoEncontradaException;
import br.com.viafood.exceptions.exception.EntidadeComDependencia;
import br.com.viafood.restaurante.exception.RestauranteNaoEncotradoExeption;
import br.com.viafood.utils.constantes.Constantes;

/**
 * @author cbgomes
 *
 */
@Service
public class CozinhaServiceImpl implements CozinhaService {

	private final CozinhaRepository repository;

	@Autowired
	public CozinhaServiceImpl(final CozinhaRepository repository) {
		this.repository = repository;
	}

	@Transactional
	@Override
	public Cozinha save(final Cozinha cozinha) {
		return this.repository.save(cozinha);
	}

	@Override
	public List<Cozinha> list() {
		return this.repository.findAll();
	}
	
	@Override
	public Cozinha getByNome(final String nome) {
		Cozinha cozinha = this.repository.nomeContaining(nome);
		if(cozinha == null) {
			throw new CozinhaNaoEncontradaException(nome);
		}
		return cozinha;			
	}

	@Override
	public Cozinha getById(Long id) {
		return this.repository.findById(id)
				.orElseThrow(() -> new CozinhaNaoEncontradaException(id));
	}

	@Transactional
	@Override
	public void remove(final Long id) {
		try {
			 this.repository.delete(this.repository.findById(id).orElseThrow(() -> {
				throw new RestauranteNaoEncotradoExeption(id);
			}));
			 
			this.repository.flush();
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeComDependencia(String.format(Constantes.ENTIDADE_DEPENDENCIAS_VINCULADAS, id));
		}
	}

	@Override
	public boolean existsCozinha(String nome) {
		return this.repository.existsByNome(nome);
	}
	
}
