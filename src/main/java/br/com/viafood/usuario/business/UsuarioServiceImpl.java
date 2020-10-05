/**
 * 
 */
package br.com.viafood.usuario.business;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.viafood.exceptions.exception.BusinessException;
import br.com.viafood.exceptions.exception.EntidadeComDependencia;
import br.com.viafood.usuario.domain.model.Usuario;
import br.com.viafood.usuario.domain.repository.UsuarioRepository;
import br.com.viafood.usuario.exception.UsuarioNaoEncontradoException;
import br.com.viafood.utils.constantes.Constantes;

/**
 * @author david
 *
 */
@Service
public class UsuarioServiceImpl implements UsuarioService {

	private UsuarioRepository repository;

	@Autowired
	public UsuarioServiceImpl(UsuarioRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<Usuario> list() {
		return this.repository.findAll();
	}

	@Transactional
	@Override
	public Usuario save(Usuario usuario) {
		this.repository.detached(usuario);
		Optional<Usuario> usuarioExistente = this.repository.findByEmail(usuario.getEmail());
		if(usuarioExistente.isPresent() && !usuarioExistente.get().equals(usuario)) {
			throw new BusinessException(String.format(Constantes.EMAIL_JA_CADASTRADO, usuario.getEmail()));
		}
		return this.repository.save(usuario);
	}

	@Override
	public Usuario getById(Long id) {
		return this.repository.findById(id).orElseThrow(() -> {
			throw new UsuarioNaoEncontradoException(id);
		});
	}

	@Transactional
	@Override
	public void remove(Long id) {
		try {
			this.repository.delete(this.repository.findById(id).orElseThrow(() -> {
				throw new UsuarioNaoEncontradoException(id);
			}));			
			this.repository.flush();
		}catch (DataIntegrityViolationException e) {
			throw new EntidadeComDependencia(
					String.format(Constantes.ENTIDADE_DEPENDENCIAS_VINCULADAS, id));
		}
	}

	@Transactional
	@Override
	public void atualizarSenha(Long id, String senhaAtual, String novaSenha) {
		Usuario senhaUsuarioBanco = this.repository.findById(id).orElseThrow(() -> {
			throw new UsuarioNaoEncontradoException(id);
		});
		
		if(senhaAtual.equals(senhaUsuarioBanco.getSenha())) {
			senhaUsuarioBanco.setSenha(novaSenha);
		}else {
			throw new BusinessException(Constantes.SENHAS_NAO_CONFEREM);
		}
	}

}
