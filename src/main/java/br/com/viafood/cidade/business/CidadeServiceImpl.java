/**
 * 
 */
package br.com.viafood.cidade.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.viafood.cidade.domain.model.Cidade;
import br.com.viafood.cidade.domain.repository.CidadeRespository;
import br.com.viafood.cidade.exception.CidadeNaoEncontradaException;
import br.com.viafood.estado.domain.repository.EstadoRepository;
import br.com.viafood.estado.exception.EstadoNaoEcontradoException;
import br.com.viafood.exceptions.exception.EntidadeComDependencia;
import br.com.viafood.utils.constantes.Constantes;

/**
 * @author cbgomes
 *
 */
@Service
public class CidadeServiceImpl implements CidadeService {

	private final CidadeRespository repository;

	private final EstadoRepository estadoRepository;

	@Autowired
	public CidadeServiceImpl(final CidadeRespository repository, EstadoRepository estadoRepository) {
		this.repository = repository;
		this.estadoRepository = estadoRepository;
	}

	@Override
	public List<Cidade> list() {
		return this.repository.findAll();
	}

	@Override
	@Transactional
	public Cidade save(Cidade cidade) {
		cidade.setEstado(this.estadoRepository.findById(cidade.getEstado().getId()).orElseThrow(() -> {
			throw new EstadoNaoEcontradoException(cidade.getEstado().getId());
		}));
		return this.repository.save(cidade);
	}

	@Override
	public Cidade getById(Long id) {
		Cidade cidade = this.repository.findById(id)
				.orElseThrow(() -> new CidadeNaoEncontradaException(id));
		return cidade;
	}

	@Transactional
	@Override
	public void remove(Long id) {
		try {
			this.repository.delete(this.repository
					.findById(id).orElseThrow(() -> new CidadeNaoEncontradaException(id)));
			this.repository.flush();
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeComDependencia(String.format(Constantes.ENTIDADE_DEPENDENCIAS_VINCULADAS, id));
		}
	}
	
}
