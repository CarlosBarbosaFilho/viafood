/**
 * 
 */
package br.com.viafood.restaurante;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.viafood.cozinha.business.CozinhaService;
import br.com.viafood.cozinha.domain.model.Cozinha;
import br.com.viafood.restaurante.asserts.AssertsJsonFilesRestaurante;
import br.com.viafood.restaurante.business.RestauranteService;
import br.com.viafood.restaurante.domain.model.Restaurante;
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
public class CadastroRestauranteTesteApi {

	private static final String ID_RESTAURANTE = "idRestaurante";

	private static final int ID_RESTAURANTE_INVALIDO = 100;

	private static final String API_V1_RESTAURANTES = "/api/v1/restaurantes";

	@LocalServerPort
	private int port;
	
	@Autowired
	private CleanDataBase cleanDataBase;
	
	private Restaurante restauranteBrasileiro;
	
	@Autowired
	private RestauranteService service;
	
	@Autowired
	private CozinhaService cozinhaService;
	
	@Before
	public void setupBefore() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = this.port;
		RestAssured.basePath = API_V1_RESTAURANTES;
		this.cleanDataBase.clearTables();
		prepararMassaDadosERetornaONumeroDeCozinhasCadastradas();		
	}
	
	@Test
	public void retornaStatus200TodosRestaurantesCadastrados() {
		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("nome", hasItem(this.restauranteBrasileiro.getNome()));
	}
	
	@Test
	public void retornaStatus201RestauranteCorretoCadastrado() {
		given()
			.body(AssertsJsonFilesRestaurante.retornaRestauranteCorretoJson())
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
	}
	
	@Test
	public void retornaStatus400RestauranteIncorretoSemCozinhaVinculadaCadastrado() {
		given()
			.body(AssertsJsonFilesRestaurante.retornaRestauranteIncorretoSemCozinhaVinculadaJson())
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.BAD_REQUEST.value());
	}
	
	@Test
	public void retornaStatus400RestauranteIncorretoSemNomeInformadoCadastrado() {
		given()
			.body(AssertsJsonFilesRestaurante.retornaRestauranteIncorretoSemNomeInformadoJson())
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.BAD_REQUEST.value());
	}
	
	@Test
	public void retornaStatus400RestauranteIncorretoTaxaFreteNegativaCadastrado() {
		given()
			.body(AssertsJsonFilesRestaurante.retornaRestauranteIncorretoTaxaFreteNegativaJson())
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.BAD_REQUEST.value());
	}
	
	@Test
	public void retornaStatus201RestauranteIncorretoTaxaFreteIgualAZeroCadastrado() {
		given()
			.body(AssertsJsonFilesRestaurante.retornaMensagemFreteGratisRestauranteIncorretoTaxaFreteIgualAZeroJson())
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
	}
	
	@Test
	public void testeRetornaStatus200ConsultaRestaurantePorIdValido() {
		given()
			.pathParam(ID_RESTAURANTE, this.restauranteBrasileiro.getId())
			.contentType(ContentType.JSON)
		.when()
			.get("/{idRestaurante}")
		.then()
		 	.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void testeRetornaStatus404ConsultaRestaurantePorIdInvalido() {
		given()
			.pathParam(ID_RESTAURANTE, ID_RESTAURANTE_INVALIDO)
			.contentType(ContentType.JSON)
		.when()
			.get("/{idRestaurante}")
		.then()
		 	.statusCode(HttpStatus.NOT_FOUND.value());
	}
	
	@Test
	public void testeStatus204RemoverRestaurantePorIdValido() {
		given()
			.pathParam(ID_RESTAURANTE, this.restauranteBrasileiro.getId())
			.contentType(ContentType.JSON)
		.when()
			.delete("/{idRestaurante}")
		.then()
	 		.statusCode(HttpStatus.NO_CONTENT.value());
	}
	
	@Test
	public void testeStatus404RemoverRestaurantePorIdQueNaoExiste() {
		given()
			.pathParam(ID_RESTAURANTE, ID_RESTAURANTE_INVALIDO)
			.contentType(ContentType.JSON)
		.when()
			.delete("/{idRestaurante}")
		.then()
	 		.statusCode(HttpStatus.NOT_FOUND.value());
	}
	
	private int prepararMassaDadosERetornaONumeroDeCozinhasCadastradas() {
		
		this.restauranteBrasileiro = new Restaurante();
		this.restauranteBrasileiro.setNome("Brasileiro");
		this.restauranteBrasileiro.setCozinha(cozinhaRestauranteTeste());
		this.restauranteBrasileiro.setTaxaFrete(BigDecimal.valueOf(20.00));
		
		Restaurante restauranteItaliano = new Restaurante();
		restauranteItaliano.setNome("Italiano");
		restauranteItaliano.setCozinha(cozinhaRestauranteTeste());
		restauranteItaliano.setTaxaFrete(BigDecimal.valueOf(10.00));
		
		this.service.save(restauranteBrasileiro);
		this.service.save(restauranteItaliano);
		
		return this.service.list().size();
	}

	private Cozinha cozinhaRestauranteTeste() {
		Cozinha cozinhaRestauranteTeste = new Cozinha();
		cozinhaRestauranteTeste.setNome("Brasileira");
		cozinhaRestauranteTeste = this.cozinhaService.save(cozinhaRestauranteTeste);
		return cozinhaRestauranteTeste;
	}
	
}
