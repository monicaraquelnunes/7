package br.com.kiosque.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Produtos {
	
	@GeneratedValue
	@Id
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "image")
	private String image;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "value")
	private float value;
	
	@Column(name = "qr")
	private String qr;
	
	@Column(name = "amount")
	private float amount;
	
	@Column(name = "unit")
	private String unit;
	

}
