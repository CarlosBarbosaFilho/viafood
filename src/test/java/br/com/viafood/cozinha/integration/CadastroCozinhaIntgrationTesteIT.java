/**
 * 
 */
package br.com.viafood.cozinha.integration;


import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.viafood.cozinha.business.CozinhaService;
import br.com.viafood.cozinha.domain.model.Cozinha;
import br.com.viafood.exceptions.exception.EntidadeComDependencia;
import br.com.viafood.exceptions.exception.EntidadeNaoEncontradaException;

/**
 * @author david
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CadastroCozinhaIntgrationTesteIT {

	@Autowired
	private CozinhaService service;

	@Test
	public void testeCadastroCozinhaComSucesso() {
		// cenário
		Cozinha cozinha = new Cozinha();
		cozinha.setNome("americana");
		// ação
		cozinha = this.service.save(cozinha);

		// validação
		assertNotEquals(cozinha, null);
		assertNotEquals(cozinha.getId(),null);
	}

	@Test(expected = ConstraintViolationException.class)
	public void testeFalhaCadastroCozinhaSemNome() {

		// cenário
		Cozinha cozinha = new Cozinha();
		cozinha.setNome(null);
		// ação
		cozinha = this.service.save(cozinha);
		assertNotEquals(cozinha.getNome(), null );
		assertEquals(cozinha.getNome(), null);
		
	}
	
	@Test(expected = EntidadeComDependencia.class)
	public void testeFalhaExcluirCozinhaEmUso() {
		//cenário
		Cozinha cozinha = this.service.getById(1L);
		
		//ação
		this.service.remove(cozinha.getId());
		
		//validação
		assertNotNull(cozinha);
		assertNotNull(cozinha.getId());
		assertNotNull(cozinha.getRestaurantes());
	}
	
	@Test(expected = EntidadeNaoEncontradaException.class)
	public void testaFalhaExcluirCozinhaInexistente() {
		this.service.remove(100L);
	}
}
