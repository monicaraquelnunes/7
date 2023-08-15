package br.com.kiosque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.kiosque.model.Produtos;

@Repository // Annotation que faz o link da tapela Produtos do banco com a classe Produtos
public interface ProdutosRepository extends JpaRepository<Produtos, Long> { // Interface ProdutosRepository extendendo JpaRepository

}
