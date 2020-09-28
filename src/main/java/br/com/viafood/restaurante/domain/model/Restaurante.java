/**
 * 
 */
package br.com.viafood.restaurante.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
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
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.viafood.cozinha.domain.model.Cozinha;
import br.com.viafood.endereco.domain.Endereco;
import br.com.viafood.pagamento.domain.model.FormaPagamento;
import br.com.viafood.produto.domain.model.Produto;
import br.com.viafood.restaurante.groupvalidation.GroupsCozinha;
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
	
	
	@NotBlank
	@Column(nullable = false)
	private String nome;

	@PositiveOrZero
	@Column(nullable = false)
	private BigDecimal taxaFrete;

	@Valid
	@ConvertGroup(from = Default.class, to=GroupsCozinha.GroupCozinhaId.class)
	@NotNull
	@ManyToOne
	@JoinColumn(name = "cozinha_id", nullable = false)
	private Cozinha cozinha;
	
	@JsonIgnore
	@Embedded
	private Endereco endereco;

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "tb_restaurantes_formas_pagamentos",
	joinColumns = @JoinColumn(name = "restaurante_id",
	referencedColumnName = "id"), 
	inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id",
	referencedColumnName = "id"))
	private List<FormaPagamento> formasPagamentos = new ArrayList<FormaPagamento>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "restaurante")
	private List<Produto> produtos = new ArrayList<Produto>();
	
	@JsonIgnore
	@Column(nullable = false, columnDefinition = "datetime")
	@CreationTimestamp
	private LocalDateTime dataCadastro;
	

	@JsonIgnore
	@Column(nullable = false, columnDefinition = "datetime")
	@UpdateTimestamp
	private LocalDateTime dataAtualizacao;
}
