/**
 * 
 */
package br.com.viafood.cozinha.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		return this.repository.list();
	}

	@Override
	public Cozinha getById(final Long id) {
		return repository.getById(id);
	}

	@Override
	public void remove(final Long id) {
		try {
			this.repository.remove(id);
			
		}catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontrada(String.format("Entidade com %d não pode ser localizada ou não existe", id));
			
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeComDependencia(
					String.format("Entidade com identificador %d não pode ser removida, existem dependências vinculadas ", id));
		}
	}

}
