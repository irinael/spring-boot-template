package com.ecommerce.microcommerce.web.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ecommerce.microcommerce.dao.ProductDao;
import com.ecommerce.microcommerce.model.Product;
import com.ecommerce.microcommerce.web.exceptions.ProductNotFoundException;

@RestController
public class ProductController {

	@Autowired
	private ProductDao dao;

	@GetMapping(value ="products")
	public List<Product> findAll() {
		return dao.findAll();
	}
	
	@GetMapping(value="products-count")
	public long count() {
		return dao.count();
	}

	@GetMapping(value ="products/{id}")
	public Product getById(@PathVariable int id) throws ProductNotFoundException {
		Product foundProduct = dao.findById(id);
		if (foundProduct==null) throw new ProductNotFoundException("Le produit portant l'identifiant " + id + " n'existe pas");
		return foundProduct;
	}
	@DeleteMapping(value="products/{id}")
	public void delete(@PathVariable int id) {
		
		dao.deleteById(id);
	}

	@PostMapping(value="products")
	public ResponseEntity<Void> add(@RequestBody Product product) {

		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(dao.save(product).getId()).toUri();

		return ResponseEntity.created(location).build();
	}
	@GetMapping(value="productsMinPrice/{minPrice}")
	public List<Product> findByPriceGreaterThan(@PathVariable double minPrice){
		List<Product> resultList = dao.findByPriceGreaterThan(minPrice);
		if (resultList.isEmpty()) throw new ProductNotFoundException("Il n'existe pas de produits dont le prix est supérieur à " + minPrice + "euros");
		return resultList;
	}
	@GetMapping(value="productsNameLike/{name}")
	public List<Product> findByNameLike(@PathVariable String name) {
		List<Product> resultList = dao.findByNameLike("%"+name+"%");
		if (resultList.isEmpty()) throw new ProductNotFoundException("Il n'existe pas de produits dont le nom contient " + name);
		return resultList;
	}
	

}
