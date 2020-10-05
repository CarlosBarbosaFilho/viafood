/**
 * 
 */
package br.com.viafood.restaurante.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import br.com.viafood.core.annotation.TaxaFrete;
import br.com.viafood.cozinha.domain.model.Cozinha;
import br.com.viafood.endereco.domain.Endereco;
import br.com.viafood.pagamento.domain.model.FormaPagamento;
import br.com.viafood.produto.domain.model.Produto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author cbgomes
 *
 */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tb_restaurantes")
public class Restaurante implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	@Column(nullable = false)
	private String nome;

	@TaxaFrete
	@PositiveOrZero
	@Column(nullable = false)
	private BigDecimal taxaFrete;

	@ManyToOne
	@JoinColumn(name = "cozinha_id", nullable = false)
	private Cozinha cozinha;
	
	@Column(nullable = false)
	private boolean ativo;
	
	@Embedded
	private Endereco endereco;

	@ManyToMany
	@JoinTable(name = "tb_restaurantes_formas_pagamentos",
	joinColumns = @JoinColumn(name = "restaurante_id",
	referencedColumnName = "id"), 
	inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id",
	referencedColumnName = "id"))
	private List<FormaPagamento> formasPagamentos = new ArrayList<FormaPagamento>();
	
	@OneToMany(mappedBy = "restaurante")
	private List<Produto> produtos = new ArrayList<Produto>();
	
	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private OffsetDateTime dataCadastro;
	
	@UpdateTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private OffsetDateTime dataAtualizacao;
	
	public void ativar() {
		setAtivo(true);
	}
	
	public void desativar() {
		setAtivo(false);
	}
}
