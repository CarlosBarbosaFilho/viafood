/**
 * 
 */
package br.com.viafood.usuario.business;

import java.util.List;

import br.com.viafood.usuario.domain.model.Usuario;

/**
 * @author david
 *
 */
public interface UsuarioService {

	public List<Usuario> list();

	public Usuario save(Usuario usuario);

	public Usuario getById(Long id);

	public void remove(Long id);
	
	public void atualizarSenha(Long id, String senhaAtual, String novaSenha);
}
