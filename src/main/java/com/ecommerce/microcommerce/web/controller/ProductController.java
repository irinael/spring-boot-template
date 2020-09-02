package com.ecommerce.microcommerce.web.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ecommerce.microcommerce.dao.ProductDao;
import com.ecommerce.microcommerce.model.Product;

@RestController
public class ProductController {
	
	@Autowired
	private ProductDao dao;
	
	@GetMapping(value ="products")
	public List<Product> findAll() {
		return dao.findAll();
	}
	
	@RequestMapping(value ="products/{id}", method=RequestMethod.GET)
	public Product getById(@PathVariable int id) {
		
		return dao.findById(id);
	}
	
	@PostMapping(value="products")
	public ResponseEntity<Void> add(@RequestBody Product product) {
		
		Product addedProduct = dao.save(product);
		
		if (addedProduct == null) {
			
			return ResponseEntity.noContent().build();
		}
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(addedProduct.getId()).toUri();
		
		
		return ResponseEntity.created(location).build();
	}

}
