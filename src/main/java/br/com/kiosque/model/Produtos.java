package br.com.kiosque.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter // Annotation do Lombok que gera os métodos Getters
@Setter // Annotation do Lombok que gera os métodos Setters
@Entity // Annotation que liga(mapeia) essa classe com a tabela no banco
public class Produtos { // Refere-se ao início da classe Produtos
	
	@GeneratedValue // Annotation que gera uma numeração automática no banco
	@Id // Annotation que mapeia a coluna Id do banco com o atributo id da Classe Produtos
	private int id; // Atributo id do tipo inteiro
	
	@Column(name = "name") // Annotation que mapeia a coluna name do banco com o atributo name da Classe Produtos
	private String name; // Atributo name do tipo String 
	
	@Column(name = "image") // Annotation que mapeia a coluna image do banco com o atributo image da Classe Produtos
	private String image; // Atributo image do tipo String
	
	@Column(name = "description") // Annotation que mapeia a coluna description do banco com o atributo description da Classe Produtos
	private String description; // Atributo description do tipo String
	
	@Column(name = "value") // Annotation que mapeia a coluna value do banco com o atributo value da Classe Produtos
	private float value; // Atributo value(valor) do tipo float
	
	@Column(name = "qr") // Annotation que mapeia a coluna qr do banco com o atributo qr da Classe Produtos
	private String qr; // Atributo qr do tipo String
	
	@Column(name = "amount") // Annotation que mapeia a coluna amount(quantidade) do banco com o atributo amount da Classe Produtos
	private float amount; // Atributo amount(quantidade) do tipo float
	
	@Column(name = "unit") // Annotation que mapeia a coluna unit(unidade) do banco com o atributo unit da Classe Produtos
	private String unit; // Atributo unit do tipo String
	

}
