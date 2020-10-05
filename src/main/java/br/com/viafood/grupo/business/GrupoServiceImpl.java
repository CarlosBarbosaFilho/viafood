/**
 * 
 */
package br.com.viafood.grupo.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.viafood.exceptions.exception.BusinessException;
import br.com.viafood.exceptions.exception.EntidadeComDependencia;
import br.com.viafood.exceptions.exception.EntidadeNaoEncontradaException;
import br.com.viafood.grupo.domain.model.Grupo;
import br.com.viafood.grupo.domain.repository.GrupoRepository;
import br.com.viafood.grupo.exception.GrupoNaoEncontradoException;
import br.com.viafood.utils.constantes.Constantes;

/**
 * @author david
 *
 */
@Service
public class GrupoServiceImpl implements GrupoService {

	private GrupoRepository repository;
	
	@Autowired
	public GrupoServiceImpl(GrupoRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public List<Grupo> list() {
		return this.repository.findAll();
	}

	@Transactional
	@Override
	public Grupo save(Grupo grupo) {
		return this.repository.save(grupo);
	}

	@Override
	public Grupo getById(Long id) {
		return this.repository.findById(id).orElseThrow(()-> {
			throw new GrupoNaoEncontradoException(String.format(Constantes.GRUPO_NAO_ENCONTRADO, id));
		});
	}

	@Transactional
	@Override
	public void remove(Long id) {
		try {
			this.repository.delete(this.repository.findById(id).orElseThrow(() -> {
				throw new GrupoNaoEncontradoException(id);
			}));
			this.repository.flush();
		} catch (BusinessException e) {
			throw new EntidadeComDependencia(e.getMessage());
		}
	}

	@Override
	public Grupo getByDescricao(String nome) {
		try {
			return this.repository.findByDescricao(nome);			
		} catch (EntidadeNaoEncontradaException e) {
			throw new GrupoNaoEncontradoException(e.getMessage());
		}
	}

}
