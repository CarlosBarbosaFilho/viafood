/**
 * 
 */
package br.com.viafood.cidade.business;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.viafood.cidade.domain.model.Cidade;
import br.com.viafood.cidade.domain.repository.CidadeRespository;
import br.com.viafood.cozinha.exception.EntidadeComDependencia;
import br.com.viafood.cozinha.exception.EntidadeNaoEncontradaException;
import br.com.viafood.estado.domain.model.Estado;
import br.com.viafood.estado.domain.repository.EstadoRepository;
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
	public Cidade save(Cidade cidade) {
		Long id = cidade.getEstado().getId();
		Optional<Estado> estado = this.estadoRepository.findById(id);
		if (estado.get() == null) {
			throw new EntidadeNaoEncontradaException(
					String.format("Endidade estado com id %d não pode ser localizada ou não existe", id));
		}
		cidade.setEstado(estado.get());
		return this.repository.save(cidade);
	}

	@Override
	public Cidade getById(Long id) {
		return this.repository.findById(id)
				.orElseThrow(() -> new EntidadeNaoEncontradaException(
						String.format(Constantes.ENTIDADE_NAO_ENCONTRADA,id)));
	}

	@Override
	public void remove(Long id) {
		try {
			this.repository.deleteById(id);

		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format("Entidade com %d não pode ser localizada ou não existe", id));

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeComDependencia(String.format(
					"Entidade com identificador %d não pode ser removida, existem dependências vinculadas ", id));
		}
	}
}
