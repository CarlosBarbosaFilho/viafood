/**
 * 
 */
package br.com.viafood.pedido.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.viafood.endereco.domain.Endereco;
import br.com.viafood.item_pedido.domain.model.ItemPedido;
import br.com.viafood.pagamento.domain.model.FormaPagamento;
import br.com.viafood.restaurante.domain.model.Restaurante;
import br.com.viafood.usuario.domain.model.Usuario;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author cbgomes
 *
 */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tb_pedidos")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;

	private BigDecimal subTotal;

	@Column(name = "taxa_frete_pedido")
	private BigDecimal taxaFrete;

	@Column(name = "valor_total_pedido")
	private BigDecimal valorTotal;

	@Column(name = "data_cadastro_pedido")
	private LocalDateTime dataCadatro;

	@Column(name = "data_confirmacao_pedido")
	private LocalDateTime dataConfirmacao;

	@Column(name = "data_cancelamento_pedido")
	private LocalDateTime dataCancelemento;

	@Column(name = "data_entrega_pedido")
	private LocalDateTime dataEntrega;

	@OneToOne
	@JoinColumn(name = "restaurante_id", nullable = false)
	private Restaurante restaurante;

	@OneToOne
	@JoinColumn(name = "forma_pagamento_id", nullable = false)
	private FormaPagamento formaPagamento;

	@OneToOne
	@JoinColumn(name = "usuario_cliente_id", nullable = false)
	private Usuario usuario;

	@OneToMany(targetEntity = ItemPedido.class, mappedBy = "pedido")
	private List<ItemPedido> itensPedidos;

	@Embedded
	private Endereco endereco;
}
