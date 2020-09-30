/**
 * 
 */
package br.com.viafood.exceptions.exceptionhandler;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Getter;

/**
 * @author cbgomes
 *
 */
@JsonInclude(value = Include.NON_NULL)
@Getter
@Builder
public class ApiErrorException {

	private Integer status;
	private String type;
	private String title;
	private String details;
	
	private String userMessage;
	private LocalDateTime timestamp;
	
	private List<Object> objects;
	
	@Getter
	@Builder
	public static class Object {
		
		private String nome;
		private String useMessage;
	}
}
