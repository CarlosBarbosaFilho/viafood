/**
 * 
 */
package br.com.viafood.grupo.domain.model;

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

import br.com.viafood.permissao.domain.model.Permissao;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author cbgomes
 *
 */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tb_grupos")
public class Grupo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;

	@Column(name = "nome_grupo")
	private String nome;

	@ManyToMany
	@JoinTable(name = "tb_grupos_permissoes", 
	joinColumns = @JoinColumn(name = "grupo_id",
	referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "permissao_id",
	referencedColumnName = "id"))
	private List<Permissao> permissoes;

}
