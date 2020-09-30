/**
 * 
 */
package br.com.viafood.core.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import br.com.viafood.core.validatorImpl.TaxaFreteGratisValidator;

/**
 * @author david
 *
 */
@Target({ ElementType.TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = { TaxaFreteGratisValidator.class })
public @interface ValidaFreteGratisDescricao {

	String message() default "descrição obrigatória inválida";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String valorField();

	String descricaoField();

	String descricaoObrigatoriaFrete();

}
