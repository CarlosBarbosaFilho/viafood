/**
 * 
 */
package br.com.viafood.cidade.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.viafood.cidade.domain.model.Cidade;
import br.com.viafood.cidade.domain.repository.CidadeRespository;
import br.com.viafood.cozinha.exception.EntidadeComDependencia;
import br.com.viafood.cozinha.exception.EntidadeNaoEncontrada;

/**
 * @author cbgomes
 *
 */
@Service
public class CidadeServiceImpl implements CidadeService {

	private final CidadeRespository repository;

	@Autowired
	public CidadeServiceImpl(final CidadeRespository repository) {
		this.repository = repository;
	}

	@Override
	public List<Cidade> list() {
		return this.repository.list();
	}

	@Override
	public Cidade save(Cidade cidade) {
		return this.repository.save(cidade);
	}

	@Override
	public Cidade getById(Long id) {
		Cidade cidade = this.repository.getById(id);
		if (cidade == null) {
			throw new EntidadeNaoEncontrada(String.format("Cidade com id %id não encontrada ou não existe", id));
		}
		return cidade;
	}

	@Override
	public void remove(Long id) {
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
