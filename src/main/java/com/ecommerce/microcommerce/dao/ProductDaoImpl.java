package com.ecommerce.microcommerce.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.ecommerce.microcommerce.model.Product;

@Repository
public class ProductDaoImpl implements ProductDao {
	
	private static List<Product> products = new ArrayList<Product>();
	static {
		products.add(new Product(1, "aspirateur", 23.6, 12));
		products.add(new Product(2, "tasse", 4, 1.5));
		products.add(new Product(3, "stylo", 3.5, 0.5));
	}

	@Override
	public List<Product> findAll() {
		
		return products;
	}

	@Override
	public Product findById(int id) {
	
		return products.stream().filter(p -> p.getId()==id).collect(Collectors.toList()).get(0);
	}

	@Override
	public Product save(Product product) {
		products.add(product);
		return product;
	}

}
