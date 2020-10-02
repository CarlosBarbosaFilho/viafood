package br.com.viafood.restaurante.domain.model.dto;

import java.math.BigDecimal;

import br.com.viafood.cozinha.domain.model.dto.CozinhaDto;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author david
 *
 */
@Getter
@Setter
public class RestauranteDto {

	private Long id;
	
	private String nome;

	private BigDecimal taxaFrete;

	private CozinhaDto cozinha;
	
}
