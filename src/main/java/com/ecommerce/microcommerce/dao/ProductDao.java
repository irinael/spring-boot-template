package com.ecommerce.microcommerce.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.microcommerce.model.Product;

@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {

	public List<Product> findAll();
	public long count();
	public Product findById(int id);
	public Product save(Product product); //persist or merge, according to the situation
	public void deleteById(Product product);
	public List<Product> findByPriceGreaterThan(double minPrice);
	public List<Product> findByNameLike(String name);
}
