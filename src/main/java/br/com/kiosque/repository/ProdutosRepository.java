package br.com.kiosque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.kiosque.model.Produtos;

@Repository
public interface ProdutosRepository extends JpaRepository<Produtos, Long> {

}
