/**
 * 
 */
package br.com.viafood.item_pedido.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import br.com.viafood.pedido.domain.model.Pedido;
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
@Table(name = "tb_itens_pedidos")
public class ItemPedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;

	@Column(name = "quantidade_itnes")
	private int quantidade;

	@Column(name = "preco_unitario")
	private BigDecimal precoUnitario;

	@Column(name = "preco_total_itens")
	private BigDecimal precoTotal;

	@Column(name = "observacao_itens")
	private String observacao;

	@OneToOne
	@JoinColumn(name = "produto_id", nullable = false)
	private Produto produto;

	@ManyToOne
	@JoinColumn(name = "pedido_id", nullable = false)
	private Pedido pedido;

	@Column(nullable = false, columnDefinition = "datetime")
	@CreationTimestamp
	private OffsetDateTime dataCadastro;
	
	@Column(nullable = false, columnDefinition = "datetime")
	@UpdateTimestamp
	private OffsetDateTime dataAtualizacao;
}
