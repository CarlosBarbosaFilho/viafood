/**
 * 
 */
package br.com.viafood.cozinha;


import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.hasSize;

import java.util.ArrayList;
import java.util.List;

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

import br.com.viafood.cozinha.asserts.AssertsJsonFilesCozinha;
import br.com.viafood.cozinha.business.CozinhaService;
import br.com.viafood.cozinha.domain.model.Cozinha;
import br.com.viafood.restaurante.asserts.AssertsJsonFilesRestaurante;
import br.com.viafood.util.CleanDataBase;
import br.com.viafood.util.ReadJsonFileResource;
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
	
	private static final int ID_INVALIDO_PARA_CONSULTA_DE_COZINHA = 100;

	@LocalServerPort
	private int port;
	
	private Cozinha cozinhaJaponesa;
	
	private String jsonCozinhaJaponesa;
	
	@Autowired
	private CleanDataBase cleanDataBase;
	
	@Autowired
	private CozinhaService service;
	
	@Before
	public void setupBefore() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = this.port;
		RestAssured.basePath = "/api/v1/cozinhas";
		this.cleanDataBase.clearTables();
		prepararMassaDadosERetornaONumeroDeCozinhasCadastradas();		
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
			.pathParam("idCozinha", this.cozinhaJaponesa.getId())
			.accept(ContentType.JSON)
		.when()
			.get("/{idCozinha}")
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("nome", equalTo(this.cozinhaJaponesa.getNome()));
	}
	
	@Test
	public void testeRetronaComFalhaCozinhaPassandoPathParamInvalido() {
		given()
			.pathParam("idCozinha", ID_INVALIDO_PARA_CONSULTA_DE_COZINHA)
			.accept(ContentType.JSON)
		.when()
			.get("/{idCozinha}")
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
	}
	
	@Test
	public void testeRetornaStatus204RemoverCozinha() {
		given()
			.pathParam("idCozinha", this.cozinhaJaponesa.getId())
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
			.body("nome", hasItems("Japonesa"));
	}
	
	@Test
	public void testeRetornaStatus201CadastroCozinha() {
		given()
			.body(AssertsJsonFilesCozinha.retornaCozinhaJson())
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
	}
	
	private int prepararMassaDadosERetornaONumeroDeCozinhasCadastradas() {
		Cozinha cozinhaBrasileira = new Cozinha();
		cozinhaBrasileira.setNome("Brasileira");
		
		this.cozinhaJaponesa = new Cozinha();
		this.cozinhaJaponesa.setNome("Japonesa");
		
		this.service.save(cozinhaBrasileira);
		this.service.save(cozinhaJaponesa);
		return this.service.list().size();
	}
	
}
