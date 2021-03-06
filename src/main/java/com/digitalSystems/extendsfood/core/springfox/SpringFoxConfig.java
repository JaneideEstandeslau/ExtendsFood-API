package com.digitalSystems.extendsfood.core.springfox;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLStreamHandler;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.amazonaws.auth.policy.Resource;
import com.digitalSystems.extendsfood.api.exceptionHandler.Problem;
import com.digitalSystems.extendsfood.api.model.CategoriaProdutoResumoModel;
import com.digitalSystems.extendsfood.api.model.PedidoResumoModel;
import com.digitalSystems.extendsfood.api.openapi.model.CategoriaProdutoResumoModelOpenApi;
import com.digitalSystems.extendsfood.api.openapi.model.PageableModelOpenApi;
import com.digitalSystems.extendsfood.api.openapi.model.PedidosResumoModelOpenApi;
import com.fasterxml.classmate.TypeResolver;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class) // S??o beans que v??o interpretar as anota????es de valida????o como, 
												// por exempro, @notnull e adicionar ao Json.
public class SpringFoxConfig implements WebMvcConfigurer {

	private TypeResolver typeResolver = new TypeResolver();
	
	@Bean
	public Docket apiDocket() {


		// Configura????es para gerar o Json da documenta????o
		return new Docket(DocumentationType.SWAGGER_2)
				
				.select()// Seleciona os endpoints que devem ser expostos na defini????o do json
					.apis(RequestHandlerSelectors.basePackage("com.digitalSystems.extendsfood"))
					.paths(PathSelectors.any())
					.build()
				.useDefaultResponseMessages(false)// Desabilita os Codigos de erros padr??o
				.globalResponseMessage(RequestMethod.GET, globalGetResponseMessages())// Descreve os status de resposta de erro globalmente nos m??todos
				.globalResponseMessage(RequestMethod.POST, globalPostPutResponseMessages())
				.globalResponseMessage(RequestMethod.PUT, globalPostPutResponseMessages())
				.globalResponseMessage(RequestMethod.DELETE, globalDeleteResponseMessages())
				
				.additionalModels(typeResolver.resolve(Problem.class))// Adiciona o Problem no Modelo de representa????o
				.ignoredParameterTypes(ServletWebRequest.class,
						URL.class, URI.class, URLStreamHandler.class, Resource.class,
						File.class, InputStream.class)
				.directModelSubstitute(Pageable.class, PageableModelOpenApi.class)// substitui o Pageable pelo
																					// PageableModelOpenApi
				.alternateTypeRules(AlternateTypeRules.newRule(
						typeResolver.resolve(Page.class, PedidoResumoModel.class),
						PedidosResumoModelOpenApi.class)) // Faz a substitui????o do Page<PedidoResumoModel> para
														// PedidosResumoModelOpenApi, ou seja, ao inv??z de retornar um Page
														// com uma lista de pedidos, vai retornar PedidosResumoModelOpenApi com
														// uma lista de cozinha model.
				.alternateTypeRules(AlternateTypeRules.newRule(
						typeResolver.resolve(Page.class, CategoriaProdutoResumoModel.class),
						CategoriaProdutoResumoModelOpenApi.class))
				
				.apiInfo(apiInfo())
				.tags(new Tag("Cidades", "Gerencia as cidades"))
				.tags(new Tag("Categorias dos Produtos", "Gerencia as Categorias dos Produtos"))
				.tags(new Tag("Cozinha", "Gerencia as Cozinha"))
				.tags(new Tag("Dias da Semana", "Gerencia os Dias da Semana"))
				.tags(new Tag("Estados", "Gerencia os Estados"))
				.tags(new Tag("Pedidos", "Gerencia os Pedidos"))
				.tags(new Tag("Formas de Pagamento", "Gerencia as Formas de Pagamento"))
				.tags(new Tag("Grupos", "Gerencia os Grupos"))
				.tags(new Tag("Usu??rios", "Gerencia os Usu??rios"))
				.tags(new Tag("Restaurantes", "Gerencia os Restaurantes"))
				.tags(new Tag("Produtos", "Gerencia os Produtos"))
				.tags(new Tag("Estat??sticas", "Estat??sticas do ExtendsFood"));
	}
	
	//Adiciona os  possiveis Status de Erro para o m??todo GET
	private List<ResponseMessage> globalGetResponseMessages() {
		return Arrays.asList(
				new ResponseMessageBuilder()
					.code(HttpStatus.INTERNAL_SERVER_ERROR.value())
					.message("Erro interno do servidor")
					.responseModel(new ModelRef("Problema"))
					.build(),
				new ResponseMessageBuilder()
					.code(HttpStatus.NOT_ACCEPTABLE.value())
					.message("Recurso n??o possui representa????o que poderia ser aceita pelo consumidor")
					.build()
			);
	}
	
	//Adiciona os  possiveis Status de Erro para os m??todos POST e PUT
	private List<ResponseMessage> globalPostPutResponseMessages() {
		return Arrays.asList(
				 new ResponseMessageBuilder()
	                .code(HttpStatus.BAD_REQUEST.value())
	                .message("Requisi????o inv??lida (erro do cliente)")
	                .responseModel(new ModelRef("Problema"))
	                .build(),
	            new ResponseMessageBuilder()
	                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
	                .message("Erro interno no servidor")
	                .responseModel(new ModelRef("Problema"))
	                .build(),
	            new ResponseMessageBuilder()
	                .code(HttpStatus.NOT_ACCEPTABLE.value())
	                .message("Recurso n??o possui representa????o que poderia ser aceita pelo consumidor")
	                .build(),
	            new ResponseMessageBuilder()
	                .code(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
	                .message("Requisi????o recusada porque o corpo est?? em um formato n??o suportado")
	                .responseModel(new ModelRef("Problema"))
	                .build()
			);
	}
	
	//Adiciona os  possiveis Status de Erro para o m??todo DELETE
	private List<ResponseMessage> globalDeleteResponseMessages() {
		return Arrays.asList(
				new ResponseMessageBuilder()
					.code(HttpStatus.BAD_REQUEST.value())
					.message("Requisi????o inv??lida (erro do cliente)")
					.responseModel(new ModelRef("Problema"))
					.build(),
				new ResponseMessageBuilder()
					.code(HttpStatus.INTERNAL_SERVER_ERROR.value())
					.message("Erro interno do servidor")
					.responseModel(new ModelRef("Problema"))
					.build()
			);
	}
	
	// Modifica informa????es no cabe??alho da documenta????o
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("ExtendsFood API")
				.description("API aberta para clientes e restaurantes")
				.version("1")
				.contact(new Contact("Digidal Systems", "https://www.digivalSystems.com", "contato@digidal.systems.com"))
				.build();
	}

	// Define onde est??o os arquivos css
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");

		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
	

}
