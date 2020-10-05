/**
 * 
 */
package br.com.viafood.restaurante.domain.model.form;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import br.com.viafood.core.annotation.TaxaFrete;
import lombok.Getter;
import lombok.Setter;

/**
 * @author david
 *
 */
@Getter
@Setter
public class RestauranteForm {
	
	@NotBlank
	private String nome;

	@NotNull
	@TaxaFrete
	@PositiveOrZero
	private BigDecimal taxaFrete;
	
	@Valid
	@NotNull
	private CozinhaIdRestauranteForm cozinha;
	
	@Valid
	@NotNull
	private EnderecoRestauranteForm endereco;
}
