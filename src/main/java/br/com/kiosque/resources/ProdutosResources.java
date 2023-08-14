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

@Tag(name = "Kiosque", description = "Aplicação Kiosque APIs")
@RestController
@RequestMapping(path = "/produtos")
public class ProdutosResources {
	
	private static final Logger logger = LoggerFactory.getLogger(ProdutosResources.class);
	private ProdutosServices produtosServices;
	
	@Autowired
	public ProdutosResources(ProdutosServices produtosServices) {
		this.produtosServices = produtosServices;
	}
	
	
	@Operation(
			summary = "Salvar produtos",
			description = "Salvar produtos.",
			tags = {"produtos", "post"})
	@ApiResponses({
		@ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Produtos.class), mediaType = "application/json") }),
		@ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
		@ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
	@PostMapping
	public ResponseEntity<Produtos> saveProdutos(@RequestBody Produtos produtos) {
		logger.info("Chamando método saveProdutos com a requisição: "+" "+produtos);
		produtosServices.saveProdutos(produtos);
		return new ResponseEntity<>(produtos, HttpStatus.OK);
	}
	
	
	@Operation(
			summary = "Recuperar produto por Id",
			description = "Obtenha um produto especificando seu id. A resposta é um produto com id, título, descrição e status publicado.",
			tags = {"produtos", "get"})
	@ApiResponses({
		@ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Produtos.class), mediaType = "application/json") }),
		@ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
		@ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
	@GetMapping(path = "/{id}")
	public ResponseEntity<Optional<Produtos>> getProdutosById(@PathVariable Long id){
		logger.info("Chamando método getProdutosById com a requisição: "+" "+id);
		Optional<Produtos> produtos;
		try {
			produtos = produtosServices.getProdutosById(id);
			return new ResponseEntity<Optional<Produtos>>(produtos, HttpStatus.OK);
		}catch (NoSuchElementException nsee){
			return new ResponseEntity<Optional<Produtos>>(HttpStatus.NOT_FOUND);
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
	public ResponseEntity<List<Produtos>> getAllprodutos(){
		logger.info("Chamando método getAllprodutos");
		List<Produtos> produtos = new ArrayList<>();
		produtos = produtosServices.getProdutos();
		return new ResponseEntity<>(produtos, HttpStatus.OK);
	}
	
	
	@Operation(
			summary = "Deletar produto por Id",
			description = "Deleta um produto especificando seu id.",
			tags = {"produtos", "delete"})
	@ApiResponses({
		@ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Produtos.class), mediaType = "application/json") }),
		@ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
		@ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
	@DeleteMapping(path="/{id}")
	public ResponseEntity<Optional<Produtos>> deleteProdutos(@PathVariable Long id){
		logger.info("Chamando método deleteProdutos com a requisição: "+" "+id);
		try {
			produtosServices.deleteProdutos(id);
			return new ResponseEntity<Optional<Produtos>>(HttpStatus.OK);
		}catch (NoSuchElementException nsee) {
			return new ResponseEntity<Optional<Produtos>>(HttpStatus.NOT_FOUND);
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
	@PutMapping(value="/{id}")
	public ResponseEntity<Optional<Produtos>> updatedProdutos(@PathVariable Long id, @RequestBody Produtos newProdutos){
		logger.info("Chamando método updatedProdutos com a requisição: "+" "+id+" "+newProdutos);
		Optional<Produtos> produto = produtosServices.updatedProdutos(id, newProdutos);
		return new ResponseEntity<Optional<Produtos>>(produto, HttpStatus.OK);
	}

}
