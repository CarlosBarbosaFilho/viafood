/**
 * 
 */
package br.com.viafood.cozinha.business;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.viafood.cozinha.domain.model.Cozinha;
import br.com.viafood.cozinha.domain.repository.CozinhaRepository;
import br.com.viafood.cozinha.exception.EntidadeComDependencia;
import br.com.viafood.cozinha.exception.EntidadeNaoEncontrada;

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

	@Override
	public void save(final Cozinha cozinha) {
		this.repository.save(cozinha);
	}

	@Override
	public List<Cozinha> list() {
		return this.repository.findAll();
	}
	
	@Override
	public Cozinha getByNome(final String nome) {
		return this.repository.nomeContaining(nome);
	}

	@Override
	public Cozinha getById(final Long id) {
		Optional<Cozinha> cozinha = repository.findById(id);
		if (cozinha.isEmpty()) {
			throw new EntidadeNaoEncontrada(String.format("Entidade cozinha com %d não localizada ou não existe", id));
		}
		return cozinha.get();
	}

	@Override
	public void remove(final Long id) {
		try {
			this.repository.deleteById(id);
			
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeComDependencia(
					String.format("Entidade com identificador %d não pode ser removida, existem dependências vinculadas ", id));
		
		}catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontrada(String.format("Entidade com %d não pode ser localizada ou não existe", id));
			
		} 
	}

	@Override
	public boolean existsCozinha(String nome) {
		return this.repository.existsByNome(nome);
	}

}
