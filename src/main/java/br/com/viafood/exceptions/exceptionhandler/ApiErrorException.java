/**
 * 
 */
package br.com.viafood.exceptions.exceptionhandler;

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
}
