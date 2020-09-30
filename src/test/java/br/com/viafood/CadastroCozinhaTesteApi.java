/**
 * 
 */
package br.com.viafood;


import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.flywaydb.core.Flyway;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.viafood.cozinha.business.CozinhaService;
import br.com.viafood.cozinha.domain.model.Cozinha;
import br.com.viafood.exceptions.exception.EntidadeNaoEncontradaException;
import br.com.viafood.util.CleanDataBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
/**
 * @author david
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class CadastroCozinhaTesteApi {
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private CleanDataBase cleanDataBase;
	
	@Autowired
	private CozinhaService service;
	
	@Before
	public void setup() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = this.port;
		RestAssured.basePath = "/api/v1/cozinhas";
		this.cleanDataBase.clearTables();
		prepararMassaDados();		
	}
	
	@Test
	public void testeRetornaStatus200ConsultaTodasCozinhas() {
		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void testeRetronaComSucessoCozinhaPassandoPathParamValido() {
		given()
			.pathParam("idCozinha", 1)
			.accept(ContentType.JSON)
		.when()
			.get("/{idCozinha}")
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("nome", equalTo("Brasileira"));
	}
	
	@Test
	public void testeRetronaComFalhaCozinhaPassandoPathParamInvalido() {
		given()
			.pathParam("idCozinha", 100)
			.accept(ContentType.JSON)
		.when()
			.get("/{idCozinha}")
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
	}
	
	@Test
	public void testeRetornaStatus204RemoverCozinha() {
		given()
			.pathParam("idCozinha", 1)
			.accept(ContentType.JSON)
		.when()
			.delete("/{idCozinha}")
		.then()
			.statusCode(HttpStatus.NO_CONTENT.value());
	}
	
	@Test
	public void testeRerotnaTotalCozinhasCadastradas() {
		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("", hasSize(2))
			.body("nome", hasItems("Japonesa"));
	}
	
	@Test
	public void testeRetornaStatus201CadastroCozinha() {
		given()
			.body("{\"nome\": \"Chinesa\"}")
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
	}
	
	private void prepararMassaDados() {
		Cozinha cozinhaBrasileira = new Cozinha();
		cozinhaBrasileira.setNome("Brasileira");
		
		Cozinha cozinhaJaponesa = new Cozinha();
		cozinhaJaponesa.setNome("Japonesa");
		
		this.service.save(cozinhaBrasileira);
		this.service.save(cozinhaJaponesa);
	}
}
