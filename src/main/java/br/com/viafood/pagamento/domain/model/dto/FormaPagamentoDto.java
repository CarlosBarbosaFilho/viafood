/**
 * 
 */
package br.com.viafood.pagamento.domain.model.dto;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

/**
 * @author david
 *
 */
@Getter
@Setter
public class FormaPagamentoDto {

	private Long id;
	
	@NotBlank
	private String descricao;
}
