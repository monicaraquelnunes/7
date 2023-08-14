package br.com.kiosque.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.kiosque.model.Produtos;
import br.com.kiosque.repository.ProdutosRepository;

@Service
public class ProdutosServices {
	private ProdutosRepository produtosRepository;
	
	@Autowired
	public ProdutosServices (ProdutosRepository produtosRepository) {
		this.produtosRepository = produtosRepository;
	}
	
	public void saveProdutos(Produtos produtos) {
		produtosRepository.save(produtos);
	}
	
	public Optional<Produtos> updatedProdutos(Long id, Produtos produtos){
		return produtosRepository.findById(id)
				.map(produto->{
					produto.setName(produtos.getName());
					produto.setImage(produtos.getImage());
					produto.setDescription(produtos.getDescription());
					produto.setValue(produtos.getValue());
					produto.setQr(produtos.getQr());
					produto.setAmount(produtos.getAmount());
					
					Produtos produtosUpdated= produtosRepository.save(produto);
					return produtosUpdated;
				});		
	}
	
	public void deleteProdutos(Long id) {
		produtosRepository.deleteById(id);
	}
	
	public Optional<Produtos> getProdutosById(Long id){
		return produtosRepository.findById(id);
	}
	
	public List<Produtos> getProdutos(){
		return produtosRepository.findAll();
	}
}
