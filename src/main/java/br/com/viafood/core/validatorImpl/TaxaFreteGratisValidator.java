/**
 * 
 */
package br.com.viafood.core.validatorImpl;

import java.math.BigDecimal;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ValidationException;

import org.springframework.beans.BeanUtils;

import br.com.viafood.core.annotation.ValidaFreteGratisDescricao;

/**
 * @author david
 *
 */

public class TaxaFreteGratisValidator implements ConstraintValidator<ValidaFreteGratisDescricao, Object>{
	
	private String valorField;
	private String descricaoField;
	private String descricacaoObrigatoria;
	
	@Override
	public void initialize(ValidaFreteGratisDescricao constraintAnnotation) {
		this.valorField = constraintAnnotation.valorField();
		this.descricaoField = constraintAnnotation.descricaoField();
		this.descricacaoObrigatoria = constraintAnnotation.descricaoObrigatoriaFrete();
	}

	@Override
	public boolean isValid(Object objetoValidacao, ConstraintValidatorContext context) {
		boolean valido = true;
		try {
			BigDecimal valorFrete = (BigDecimal) BeanUtils.getPropertyDescriptor(objetoValidacao.getClass(), valorField)
					.getReadMethod().invoke(objetoValidacao);
			
			String descricao = (String) BeanUtils.getPropertyDescriptor(objetoValidacao.getClass(), descricaoField)
					.getReadMethod().invoke(objetoValidacao);
			
			if(valorFrete != null && BigDecimal.ZERO.compareTo(valorFrete) == 0 && descricao != null) {
				valido = descricao.toLowerCase().contains(this.descricacaoObrigatoria.toLowerCase());
			}

			return valido;
			
		} catch (Exception e) {
			throw new ValidationException(e);
		}
				
	}
	
	
}
