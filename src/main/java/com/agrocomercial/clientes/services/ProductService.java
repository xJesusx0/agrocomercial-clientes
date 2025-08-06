package com.agrocomercial.clientes.services;

import com.agrocomercial.clientes.models.Product;

import java.util.List;

public interface ProductService {
  List<Product> findAll();

  Product save(Product product);
}
