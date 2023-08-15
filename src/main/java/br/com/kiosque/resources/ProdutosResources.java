package br.com.kiosque.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.kiosque.model.Produtos;
import br.com.kiosque.service.ProdutosServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Kiosque", description = "Aplicação Kiosque APIs") // Annotation refere-se ao nome do grupo Kiosque no Swagger com a Descrição "Aplicação Kiosque APIs"
@RestController // Annotation que expõe os endpoints(os atributos) para acesso externo
@RequestMapping(path = "/produtos") // Annotation que fornece um caminho para acessar no browser(http://localhost:8080/produtos)
public class ProdutosResources { // Início da classe ProdutosResources
	
	private static final Logger logger = LoggerFactory.getLogger(ProdutosResources.class); // Implementação do Log4j no projeto(implementando o objeto para ouvir o logger)
	private ProdutosServices produtosServices; // Declarando o objeto para acessar os métodos da classe ProdutosService
	
	@Autowired // Annotation para realizar uma injeção de dependência automática em classes gerenciadas pelo contêiner Spring. 
	public ProdutosResources(ProdutosServices produtosServices) { // Construtor servindo para iniciar de forma automática o atributo produtosServices
		this.produtosServices = produtosServices; // Alimentando o atributo produtosServices recebendo o argumento produtosServices
	}
	
	
	@Operation( // Annotation(do Swagger) usada para adicionar informações específicas ao método saveProdutos(endpoint) que está sendo documentado pelo Swagger
			summary = "Salvar produtos", // Descrição para personalizar o Post no Swagger
			description = "Salvar produtos.", // Descrição para personalizar o Post no Swagger
			tags = {"produtos", "post"}) // Descrição para personalizar o Post no Swagger
	@ApiResponses({ // Annotation(do Swagger) que permite definir as respostas possíveis que um endpoint pode retornar, juntamente com códigos de status HTTP
		@ApiResponse(responseCode = "201", content = { @Content(schema = @Schema(implementation = Produtos.class), mediaType = "application/json") }),
		@ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
		@ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
	@PostMapping // Anotate que serve para cadastrar(criar e salvar) um produto
	public ResponseEntity<Produtos> saveProdutos(@RequestBody Produtos produtos) { // Método saveProdutos com argumento produtos
		logger.info("Chamando método saveProdutos com a requisição: "+" "+produtos); // Descrição personalizada para o logger
		produtosServices.saveProdutos(produtos); // Método saveProdutos está processando o atributo produtos no banco de dados
		return new ResponseEntity<>(produtos, HttpStatus.OK); // Está retornando o código Http 200
	}
	
	
	@Operation( // Annotation(do Swagger) usada para adicionar informações específicas ao método getProdutosById(endpoint) que está sendo documentado pelo Swagger
			summary = "Recuperar produto por Id", // Descrições para personalizar o Get no Swagger
			description = "Obtenha um produto especificando seu id. A resposta é um produto com id, título, descrição e status publicado.",
			tags = {"produtos", "get"})
	@ApiResponses({ // Annotation(do Swagger) que permite definir as respostas possíveis do endpoint GET pode retornar, juntamente com códigos de status HTTP
		@ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Produtos.class), mediaType = "application/json") }),
		@ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
		@ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
	@GetMapping(path = "/{id}") // Anotate que serve para consultar(ler) um produto
	public ResponseEntity<Optional<Produtos>> getProdutosById(@PathVariable Long id){ // Método getProdutosById com argumento id
		logger.info("Chamando método getProdutosById com a requisição: "+" "+id); // Descrição personalizada para o logger
		Optional<Produtos> produtos; // Atributo produtos
		try { // Tentando fazer algo
			produtos = produtosServices.getProdutosById(id); // Atributo produtos está recebendo a consulta pelo id
			return new ResponseEntity<Optional<Produtos>>(produtos, HttpStatus.OK); // Está retornando produtos e o código Http 200
		}catch (NoSuchElementException nsee){ // Lançando exceção
			return new ResponseEntity<Optional<Produtos>>(HttpStatus.NOT_FOUND); // Está retornando o código do Http 404
		}
	}
	
	
	@Operation(
			summary = "Recuperar produtos",
			description = "Recuperar produtos.",
			tags = {"produtos", "get"})
	@ApiResponses({
		@ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Produtos.class), mediaType = "application/json") }),
		@ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
		@ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
	@GetMapping
	public ResponseEntity<List<Produtos>> getAllprodutos(){ // Método getAllprodutos
		logger.info("Chamando método getAllprodutos");
		List<Produtos> produtos = new ArrayList<>(); // Criando um objeto produto
		produtos = produtosServices.getProdutos(); // O Objeto produtos recebe a consulta do banco através do método getAllprodutos
		return new ResponseEntity<>(produtos, HttpStatus.OK); // Retorna o objeto produtos e o código Http 200
	}
	
	
	@Operation(
			summary = "Deletar produto por Id",
			description = "Deleta um produto especificando seu id.",
			tags = {"produtos", "delete"})
	@ApiResponses({
		@ApiResponse(responseCode = "204", content = { @Content(schema = @Schema(implementation = Produtos.class), mediaType = "application/json") }),
		@ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
		@ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
	@DeleteMapping(path="/{id}") // Anotate que serve para excluir(deletar) um produto
	public ResponseEntity<Optional<Produtos>> deleteProdutos(@PathVariable Long id){ // Método deleteProdutos com argumento id
		logger.info("Chamando método deleteProdutos com a requisição: "+" "+id);
		try {
			produtosServices.deleteProdutos(id); // Executando o método para excluir um registro do banco
			return new ResponseEntity<Optional<Produtos>>(HttpStatus.OK); // Está retornando o código Http 200
		}catch (NoSuchElementException nsee) { // Lançando exceção
			return new ResponseEntity<Optional<Produtos>>(HttpStatus.NOT_FOUND); // Está retornando o código do Http 404
		}
	}
	
	
	@Operation(
			summary = "Alterar produto por Id",
			description = "Altere um produto especificando seu id.",
			tags = {"produtos", "put"})
	@ApiResponses({
		@ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Produtos.class), mediaType = "application/json") }),
		@ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
		@ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
	@PutMapping(value="/{id}") // Anotate que serve para atualizar(update) um produto
	public ResponseEntity<Optional<Produtos>> updatedProdutos(@PathVariable Long id, @RequestBody Produtos newProdutos){ // Método updatedProdutos com argumento id e com o argumento newProdutos
		logger.info("Chamando método updatedProdutos com a requisição: "+" "+id+" "+newProdutos);
		Optional<Produtos> produto = produtosServices.updatedProdutos(id, newProdutos); // Salvando a atualização no banco
		return new ResponseEntity<Optional<Produtos>>(produto, HttpStatus.OK);
	}

}
