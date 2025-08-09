package com.agrocomercial.clientes.services.impl.database;

import com.agrocomercial.clientes.models.Product;
import com.agrocomercial.clientes.repositories.ProductRepository;
import com.agrocomercial.clientes.services.ProductService;

import java.util.List;

public class DatabaseProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;

	public DatabaseProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	@Override
	public Product save(Product product) {
		return null;
	}
}
