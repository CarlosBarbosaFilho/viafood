/**
 * 
 */
package br.com.viafood.cozinha.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.viafood.cozinha.domain.model.Cozinha;
import br.com.viafood.cozinha.domain.repository.CozinhaRepository;
import br.com.viafood.cozinha.exception.EntidadeComDependencia;
import br.com.viafood.cozinha.exception.EntidadeNaoEncontradaException;
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
			throw new EntidadeNaoEncontradaException(Constantes.ENTIDADE_NAO_ENCONTRADA);
		}
		return cozinha;			
	}

	@Override
	public Cozinha getById(Long id) {
		return this.repository.findById(id)
				.orElseThrow(() -> new EntidadeNaoEncontradaException(
						String.format(Constantes.ENTIDADE_NAO_ENCONTRADA,id)));
	}

	@Override
	public void remove(final Long id) {
		try {
			this.repository.deleteById(id);
			
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeComDependencia(
					String.format(Constantes.ENTIDADE_DEPENDÊNCIAS_VINCULADAS, id));
		
		}catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format(Constantes.ENTIDADE_NAO_ENCONTRADA, id));
			
		} 
	}

	@Override
	public boolean existsCozinha(String nome) {
		return this.repository.existsByNome(nome);
	}
	
}
