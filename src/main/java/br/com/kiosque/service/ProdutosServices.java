package br.com.kiosque.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.kiosque.model.Produtos;
import br.com.kiosque.repository.ProdutosRepository;

@Service // IMPORTANTE essa annotation sempre que a classe for Services
public class ProdutosServices { // Iniciando a classe ProdutosServices
	private ProdutosRepository produtosRepository; // Criando o atributo da Interface ProdutosRepository
	
	@Autowired // Annotation responsável por injetar o objeto na classe Services
	public ProdutosServices (ProdutosRepository produtosRepository) { // Construtor servindo para iniciar de forma automática o atributo produtosRepository
		this.produtosRepository = produtosRepository; // Alimentando o atributo produtosRepository com o argumento produtosRepository
	}
	
	public void saveProdutos(Produtos produtos) { // Método saveProdutos com argumento produtos retornando void(nada)
		produtosRepository.save(produtos); // Método save está processando o atributo produtos no banco de dados
	}
	
	public Optional<Produtos> updatedProdutos(Long id, Produtos produtos){ // Método updatedProdutos com argumento id e argumento produtos retornando Optional Produtos
		return produtosRepository.findById(id) // o método findById realiza consulta com argumento id
				.map(produto->{ // resultado da consulta do findById
					produto.setName(produtos.getName()); // substituindo o Name(produto) da consulta pelo Name(produtos) do argumento
					produto.setImage(produtos.getImage()); // substituindo a Image(produto) da consulta pela Image(produtos) do argumento
					produto.setDescription(produtos.getDescription()); // substituindo o Description(produto) da consulta pelo Description(produtos) do argumento
					produto.setValue(produtos.getValue()); // substituindo o Value(produto) da consulta pelo Value(produtos) do argumento
					produto.setQr(produtos.getQr()); // substituindo o Qr(produto) da consulta pelo Qr(produtos) do argumento
					produto.setAmount(produtos.getAmount()); // substituindo o Amount(produto) da consulta pelo Amount(produtos) do argumento
					
					Produtos produtosUpdated= produtosRepository.save(produto); // salvando o produto no banco
					return produtosUpdated; // retornando produtosUpdated
				});		
	}
	
	public void deleteProdutos(Long id) { // Método deleteProdutos com argumento id do tipo Long retornando um void(nada)
		produtosRepository.deleteById(id); // o método deleteById realiza consulta com argumento id
	}
	
	public Optional<Produtos> getProdutosById(Long id){ // Método getProdutosById com argumento id do tipo Long, retornando um Optional Produtos
		return produtosRepository.findById(id); // o método findById realiza consulta com argumento id
	}
	
	public List<Produtos> getProdutos(){ // Método getProdutos retornando uma lista de Produtos
		return produtosRepository.findAll(); // executa o método findAll retornando uma consulta com todos os registros do banco
	}
}
