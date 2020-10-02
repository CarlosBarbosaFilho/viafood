/**
 * 
 */
package br.com.viafood.cidade.domain.model.dto;

import br.com.viafood.estado.domain.model.dto.EstadoDto;
import lombok.Getter;
import lombok.Setter;

/**
 * @author david
 *
 */
@Getter
@Setter
public class CidadeDto {

	private Long id;
	
	private String nome;
	
	private EstadoDto estado;

}
