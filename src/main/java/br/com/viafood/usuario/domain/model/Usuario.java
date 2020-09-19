/**
 * 
 */
package br.com.viafood.usuario.domain.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import br.com.viafood.grupo.domain.model.Grupo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author cbgomes
 *
 */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tb_usuarios")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;

	@Column(name = "nome_usuario", nullable = false)
	private String nome;

	@Column(name = "email_usuario", nullable = false)
	private String email;

	@Column(name = "senha_usuario", nullable = false)
	private String senha;

	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private LocalDateTime dataCadastro;

	@ManyToMany
	@JoinTable(name = "tb_usuarios_grupos",
	joinColumns = @JoinColumn(name = "usuario_id",
	referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "grupo_id",
	referencedColumnName = "id"))
	private List<Grupo> grupos;
	
}
