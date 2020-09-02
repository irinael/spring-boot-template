package com.ecommerce.microcommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"id", "prixAchat"})
public class Product {
	private int id;
	private String name;
	private double price;
	@JsonIgnore
	private double prixAchat;
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Product(int id, String name, double price, double prixAchat) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.prixAchat = prixAchat;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getPrixAchat() {
		return prixAchat;
	}
	public void setPrixAchat(double prixAchat) {
		this.prixAchat = prixAchat;
	}
	
	

}