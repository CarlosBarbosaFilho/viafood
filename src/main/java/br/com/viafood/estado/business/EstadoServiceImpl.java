/**
 * 
 */
package br.com.viafood.estado.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.viafood.estado.domain.model.Estado;
import br.com.viafood.estado.domain.repository.EstadoRepository;
import br.com.viafood.estado.exception.EstadoNaoEcontradoException;
import br.com.viafood.exceptions.exception.EntidadeComDependencia;
import br.com.viafood.utils.constantes.Constantes;

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
		return this.repository.findById(id).orElseThrow(() -> {
			throw new EstadoNaoEcontradoException(id);
		});
	}

	@Override
	public void remove(final Long id) {
		try {
			this.repository.delete(this.repository.findById(id).orElseThrow(() -> {
				throw new EstadoNaoEcontradoException(id);
			}));
			this.repository.flush();
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeComDependencia(
					String.format(Constantes.ENTIDADE_DEPENDENCIAS_VINCULADAS, id));
		}
	}

}
