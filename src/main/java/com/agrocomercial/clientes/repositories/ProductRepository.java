package com.agrocomercial.clientes.repositories;

import com.agrocomercial.clientes.models.Product;

import java.util.List;

public interface ProductRepository {
	List<Product> findAll();
	Product save(Product product);
}
