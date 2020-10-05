/**
 * 
 */
package br.com.viafood.pagamento.domain.model.form;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

/**
 * @author david
 *
 */
@Getter
@Setter
public class FormaPagamentoForm {

	@NotBlank
	private String descricao;
	
}
