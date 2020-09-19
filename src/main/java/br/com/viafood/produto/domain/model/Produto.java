/**
 * 
 */
package br.com.viafood.produto.domain.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.viafood.restaurante.domain.model.Restaurante;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author cbgomes
 *
 */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tb_produtos")
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;

	@Column(name = "nome_produto", nullable = false)
	private String nome;
	
	@Column(name = "descricao_produto", nullable = false)
	private String descricao;
	
	@Column(name = "preco_produto", nullable = false)
	private BigDecimal preco;
	
	@Column(name = "ativo", nullable = false)
	private boolean ativo;
	
	@ManyToOne
	@JoinColumn(name = "restaurante_id")
	private Restaurante restaurante;

}
