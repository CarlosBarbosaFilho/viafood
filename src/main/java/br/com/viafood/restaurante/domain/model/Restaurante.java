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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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

	@Column(name = "nome_restaurante", nullable = false)
	private String nome;

	@Column(name = "taxa_frete", nullable = false)
	private BigDecimal taxaFrete;

//	@JsonIgnore
	@ManyToOne //(fetch = FetchType.LAZY)
	@JoinColumn(name = "cozinha_id", nullable = false)
	private Cozinha cozinha;
	
	@JsonIgnore
	@Embedded
	private Endereco endereco;

//	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "tb_restautantes_formasPagamentos",
	joinColumns = @JoinColumn(name = "restaurante_id",
	referencedColumnName = "id"), 
	inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id",
	referencedColumnName = "id"))
	private List<FormaPagamento> formasPagamentos = new ArrayList<FormaPagamento>();
	
	@JsonIgnore
	@OneToMany
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
