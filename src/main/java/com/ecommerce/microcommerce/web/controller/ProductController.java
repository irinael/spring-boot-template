package com.ecommerce.microcommerce.web.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

@RestController
@Api(tags = {"MyEcommerceAPI"})
public class ProductController {

	@Autowired
	private ProductDao dao;

	@ApiOperation(value="Gets a list of all registered products")
	@GetMapping(value ="products")
	public List<Product> findAll() {
		return dao.findAll();
	}
	@ApiOperation(value="Gets the number of the registered products")
	@GetMapping(value="products-count")
	public long count() {
		return dao.count();
	}

	@ApiOperation(value="Gets one specific product according to its ID")
	@GetMapping(value ="products/{id}")
	public Product getById(@PathVariable int id) throws ProductNotFoundException {
		Product foundProduct = dao.findById(id);
		if (foundProduct==null) throw new ProductNotFoundException("Le produit portant l'identifiant " + id + " n'existe pas");
		return foundProduct;
	}
	@ApiOperation(value="Deletes one specific product according to its ID")
	@DeleteMapping(value="products/{id}")
	public void delete(@PathVariable int id) {

		dao.deleteById(id);
	}

	@ApiOperation(value="Adds a product")
	@PostMapping(value="products")
	public ResponseEntity<Void> add(@RequestBody Product product) {


		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(dao.save(product).getId()).toUri();

		return ResponseEntity.created(location).build();
	}

	@ApiOperation(value="Gets the products which price is greater than a specific double")
	@GetMapping(value="productsMinPrice/{minPrice}")
	public List<Product> findByPriceGreaterThan(@PathVariable double minPrice){
		List<Product> resultList = dao.findByPriceGreaterThan(minPrice);
		if (resultList.isEmpty()) throw new ProductNotFoundException("Il n'existe pas de produits dont le prix est supérieur à " + minPrice + "euros");
		return resultList;
	}

	@ApiOperation(value="Gets the products which name matches, totally or partially, a specific string")
	@GetMapping(value="productsNameLike/{name}")
	public List<Product> findByNameLike(@PathVariable String name) {
		List<Product> resultList = dao.findByNameLike("%"+name+"%");
		if (resultList.isEmpty()) throw new ProductNotFoundException("Il n'existe pas de produits dont le nom contient " + name);
		return resultList;
	}


}
