/**
 * 
 */
package br.com.viafood.usuario.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.viafood.exceptions.exception.EntidadeNaoEncontradaException;
import br.com.viafood.usuario.business.UsuarioService;
import br.com.viafood.usuario.domain.model.Usuario;
import br.com.viafood.usuario.domain.model.converter.UsuarioConverterDTO;
import br.com.viafood.usuario.domain.model.converter.UsuarioConverterForm;
import br.com.viafood.usuario.domain.model.dto.UsuarioDto;
import br.com.viafood.usuario.domain.model.form.UsuarioForm;
import br.com.viafood.usuario.domain.model.form.UsuarioFormAtualizarSenha;
import br.com.viafood.usuario.domain.model.form.UsuarioSenhaForm;
import br.com.viafood.usuario.exception.UsuarioNaoEncontradoException;

/**
 * @author david
 *
 */
@RestController
@RequestMapping("/api/v1")
public class UsuarioResourceRest {

	private final UsuarioService service;
	
	private final UsuarioConverterDTO usuarioConverterDTO;
	
	private final UsuarioConverterForm usuarioConverterForm;
	
	@Autowired
	public UsuarioResourceRest(final UsuarioService service, final UsuarioConverterDTO usuarioConverterDTO,
			final UsuarioConverterForm usuarioConverterForm) {
		this.service = service;
		this.usuarioConverterDTO = usuarioConverterDTO;
		this.usuarioConverterForm = usuarioConverterForm;
	}
	
	@GetMapping("/usuarios")
	@ResponseStatus(value = HttpStatus.OK)
	public List<UsuarioDto> list(){
		return this.usuarioConverterDTO.restaurantesDtos(this.service.list());
	}
	
	@PostMapping("/usuarios")
	@ResponseStatus(value = HttpStatus.CREATED)
	public UsuarioDto save(@RequestBody @Valid final UsuarioSenhaForm usuarioForm) {
		return  this.usuarioConverterDTO.ToDto(this.service
				.save(this.usuarioConverterForm.ToUsuarioForm(usuarioForm)));
	}
	
	@PutMapping("/usuarios/{id}")
	@ResponseStatus(value = HttpStatus.CREATED)
	public UsuarioDto edit(@PathVariable final Long id, @RequestBody final UsuarioForm usuarioForm) {
		try {
			Usuario usuarioAtual = this.service.getById(id);
			this.usuarioConverterForm.copyUsuarioFormToUsuario(usuarioForm, usuarioAtual);
			return this.usuarioConverterDTO.ToDto(this.service.save(usuarioAtual));
		} catch (EntidadeNaoEncontradaException e) {
			throw new UsuarioNaoEncontradoException(e.getMessage());
		}
	}
	
	@GetMapping("/usuarios/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public UsuarioDto getById(@PathVariable final Long id) {
		return this.usuarioConverterDTO.ToDto(this.service.getById(id));
	}
	
	@DeleteMapping("/usuarios/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void remove(@PathVariable final Long id) {
		this.service.remove(id);
	}
	
	@PutMapping("/usuarios/{id}/atualiza-senha")
	public String updateSenhaUsuario(@PathVariable final Long id, @RequestBody final UsuarioFormAtualizarSenha usuarioFormAtualizaSenha) {
		String senhaAtual = usuarioFormAtualizaSenha.getSenhaAtual();
		String novaSenha = usuarioFormAtualizaSenha.getNovaSenha();
		this.service.atualizarSenha(id, senhaAtual, novaSenha);
		return "Senha alterada com sucesso";
	}
}
