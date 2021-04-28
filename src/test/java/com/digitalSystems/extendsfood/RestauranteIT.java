package com.digitalSystems.extendsfood;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.digitalSystems.extendsfood.domain.model.Cidade;
import com.digitalSystems.extendsfood.domain.model.Cozinha;
import com.digitalSystems.extendsfood.domain.model.Endereco;
import com.digitalSystems.extendsfood.domain.model.Estado;
import com.digitalSystems.extendsfood.domain.model.Restaurante;
import com.digitalSystems.extendsfood.domain.repository.CidadeRepository;
import com.digitalSystems.extendsfood.domain.repository.CozinhaRepository;
import com.digitalSystems.extendsfood.domain.repository.EnderecoRepository;
import com.digitalSystems.extendsfood.domain.repository.EstadoRepository;
import com.digitalSystems.extendsfood.domain.repository.RestauranteRepository;
import com.digitalSystems.extendsfood.util.DatabaseCleaner;
import com.digitalSystems.extendsfood.util.ResourceUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")	
public class RestauranteIT {
	
	private static final String DADOS_INVALIDOS_PROBLEM_TYPE = "Dados invalidos";
	private static final String VIOLACAO_REGRA_NEGOCIO_PROBLEM_TYPE = "Violação de regra de negócio";
	 private static final int RESTAURANTE_ID_INEXISTENTE = 100;
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private DatabaseCleaner databaseCleaner;
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
//	@Autowired
//	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository EstadoRepository;
	
	private Restaurante burgerTopRestaurante;
	private String jsonCorretoRestuarntePizzaria;
	private String jsonRestauranteSemDadosObrigatorios;
	private String jsonRestauranteSemCozinha;
	private String jsonRestauranteCozinhaInexistente;
	private String jsonRestauranteSemEndereco;
	
	@Before
	public void setUpClass() {

		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

		RestAssured.port = port;
		RestAssured.basePath = "/restaurante";

		jsonCorretoRestuarntePizzaria = ResourceUtils
				.getContentFromResource("/json/correto/restaurante-correto.json");

		jsonRestauranteSemDadosObrigatorios = ResourceUtils
				.getContentFromResource("/json/incorreto/restaurante_sem_dados_obrigatorios.json");
		
		jsonRestauranteSemCozinha = ResourceUtils
				.getContentFromResource("/json/incorreto/restaurante_sem_cozinha.json");
		
		jsonRestauranteCozinhaInexistente = ResourceUtils
				.getContentFromResource("/json/incorreto/restaurante_cozinha_inexistente.json");
		
		jsonRestauranteSemEndereco = ResourceUtils
				.getContentFromResource("/json/incorreto/restaurante_sem_endereco.json");

		databaseCleaner.clearTables();
		prepararDados();
	}
	
	@Test
	public void deveRetornar200_QuandoConsultarRestaurantes() {
		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void deveRetornarRespostaEStatusCorretos_QuandoConsultarRestauranteExistente() {
		given()
			.pathParam("restauranteId", burgerTopRestaurante.getId())
			.accept(ContentType.JSON)
		.when()
			.get("/{restauranteId}")
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("nome", equalTo(burgerTopRestaurante.getNome()));
	}
	
	@Test
	public void deveRetornarStatus201_QuandoCadastrarRestaurante() {
		given()
			.body(jsonCorretoRestuarntePizzaria)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
	}
	
	@Test
	public void deveRetornarStatus400_QuandoCadastrarRestauranteSemDadosObrigatorios() {
		given()
			.body(jsonRestauranteSemDadosObrigatorios)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.BAD_REQUEST.value())
			.body("title", equalTo(DADOS_INVALIDOS_PROBLEM_TYPE));
	}
	
	@Test
	public void deveRetornarStatus400_QuandoCadastrarRestauranteSemCozinha() {
		given()
			.body(jsonRestauranteSemCozinha)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.BAD_REQUEST.value())
			.body("title", equalTo(DADOS_INVALIDOS_PROBLEM_TYPE));
	}
	
	@Test
	public void deveRetornarStatus400_QuandoCadastrarRestauranteComCozinhaInexistente() {
		given()
			.body(jsonRestauranteCozinhaInexistente)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.BAD_REQUEST.value())
			.body("title", equalTo(VIOLACAO_REGRA_NEGOCIO_PROBLEM_TYPE));
	}
	
	@Test
	public void deveRetornarStatus400_QuandoCadastrarRestauranteSemEndereco() {
		given()
			.body(jsonRestauranteSemEndereco)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.BAD_REQUEST.value())
			.body("title", equalTo(DADOS_INVALIDOS_PROBLEM_TYPE));
	}
	
	 @Test
	    public void deveRetornarStatus404_QuandoConsultarRestauranteInexistente() {
	        given()
	            .pathParam("restauranteId", RESTAURANTE_ID_INEXISTENTE)
	            .accept(ContentType.JSON)
	        .when()
	            .get("/{restauranteId}")
	        .then()
	            .statusCode(HttpStatus.NOT_FOUND.value());
	    }
	
	private void prepararDados() {
		
		Estado estado = new Estado();
		estado.setNome("Paraíba");
		EstadoRepository.save(estado);
		
		Cidade cidade = new Cidade();
		cidade.setNome("Campina Grande");
		cidade.setEstado(estado);
		cidadeRepository.save(cidade);
		
		Endereco endereco1 = new Endereco();
		endereco1.setCep("58.410-896");
		endereco1.setRua("Sebastião Vieira");
		endereco1.setNumero("210");
		endereco1.setBairro("Catolé");
		endereco1.setCidade(cidade);
//		enderecoRepository.save(endereco1);
		
		Endereco endereco2 = new Endereco();
		endereco2.setCep("58.410-896");
		endereco2.setRua("João Viana");
		endereco2.setNumero("120");
		endereco2.setBairro("Catolé");
		endereco2.setCidade(cidade);
//		enderecoRepository.save(endereco2);
		
		Cozinha cozinhaBrasileira = new Cozinha();
        cozinhaBrasileira.setNome("Brasileira");
        cozinhaRepository.save(cozinhaBrasileira);

        Cozinha cozinhaAmericana = new Cozinha();
        cozinhaAmericana.setNome("Americana");
        cozinhaRepository.save(cozinhaAmericana);
        
        burgerTopRestaurante = new Restaurante();
        burgerTopRestaurante.setNome("Burger Top");
        burgerTopRestaurante.setCnpj("35.919.473/0001-71");
        burgerTopRestaurante.setTelefone("(83) 95989-8974"); 
        burgerTopRestaurante.setTaxaFrete(new BigDecimal(10));
        burgerTopRestaurante.setAberto(false);
        burgerTopRestaurante.setAtivo(true);
        burgerTopRestaurante.setHorarioInicioFuncionamento("08:00");
        burgerTopRestaurante.setHorarioFimFuncionamento("17:00");
        burgerTopRestaurante.setDataAtualizacao(LocalDateTime.now());
        burgerTopRestaurante.setCozinha(cozinhaAmericana);
        burgerTopRestaurante.setEndereco(endereco1);
        restauranteRepository.save(burgerTopRestaurante);
        
        Restaurante comidaMineiraRestaurante = new Restaurante();
        comidaMineiraRestaurante.setNome("Comida Mineira");
        comidaMineiraRestaurante.setCnpj("00.419.695/0001-40");
        comidaMineiraRestaurante.setTelefone("(83) 95989-8974"); 
        comidaMineiraRestaurante.setTaxaFrete(new BigDecimal(10));
        comidaMineiraRestaurante.setAberto(false);
        comidaMineiraRestaurante.setAtivo(true);
        comidaMineiraRestaurante.setHorarioInicioFuncionamento("08:00");
        comidaMineiraRestaurante.setHorarioFimFuncionamento("17:00");
        comidaMineiraRestaurante.setDataAtualizacao(LocalDateTime.now());
        comidaMineiraRestaurante.setCozinha(cozinhaBrasileira);
        comidaMineiraRestaurante.setEndereco(endereco2);
        restauranteRepository.save(comidaMineiraRestaurante);
	}
}
