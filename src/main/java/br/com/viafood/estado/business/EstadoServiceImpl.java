/**
 * 
 */
package br.com.viafood.estado.business;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.viafood.cozinha.exception.EntidadeComDependencia;
import br.com.viafood.cozinha.exception.EntidadeNaoEncontradaException;
import br.com.viafood.estado.domain.model.Estado;
import br.com.viafood.estado.domain.repository.EstadoRepository;

/**
 * @author cbgomes
 *
 */
@Service
public class EstadoServiceImpl implements EstadoService {
	
	public final EstadoRepository repository;
	
	 
	@Autowired
	public EstadoServiceImpl(final EstadoRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<Estado> list() {
		return this.repository.findAll();
	}

	@Override
	public Estado save(final Estado estado) {
		return this.repository.save(estado);
	}

	@Override
	public Estado getById(final Long id) {
		Optional<Estado> estado = this.repository.findById(id);
		if(estado.isPresent()) {
			throw new EntidadeNaoEncontradaException(
					String.format("Entidade estado com %d não localizada ou não existe", id));
		}
		return estado.get();			
	}

	@Override
	public void remove(final Long id) {
		try {
			this.repository.deleteById(id);
			
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
					String.format("Entidade estado com %d não localizada ou não existe", id));
			
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeComDependencia(
					String.format("Entidade com %d não pode ser removida, existem dependências vinculdas", id));
		}
	}

}
