/**
 * 
 */
package br.com.viafood.pedido.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
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

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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

	@Column(nullable = false)
	private BigDecimal taxaFrete;

	@Column(nullable = false)
	private BigDecimal valorTotal;

	@Column(nullable = false)
	private LocalDateTime dataCadatro;

	@Column(nullable = false)
	private LocalDateTime dataConfirmacao;

	@Column(nullable = false)
	private LocalDateTime dataCancelemento;

	@Column(nullable = false)
	private LocalDateTime dataEntrega;

	@OneToOne
	@JoinColumn(nullable = false)
	private Restaurante restaurante;

	@OneToOne
	@JoinColumn(nullable = false)
	private FormaPagamento formaPagamento;

	@OneToOne
	@JoinColumn(name = "usuario_cliente_id", nullable = false)
	private Usuario usuario;

	@OneToMany(mappedBy = "pedido")
	private List<ItemPedido> itensPedidos;

	@Embedded
	private Endereco endereco;
	
	@Column(nullable = false, columnDefinition = "datetime")
	@CreationTimestamp
	private OffsetDateTime dataCadastro;
	
	@Column(nullable = false, columnDefinition = "datetime")
	@UpdateTimestamp
	private OffsetDateTime dataAtualizacao;
}
