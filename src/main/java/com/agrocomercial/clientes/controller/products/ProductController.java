package com.agrocomercial.clientes.controller.products;

import com.agrocomercial.clientes.events.ProductCreatedEventListener;
import com.agrocomercial.clientes.models.Product;
import com.agrocomercial.clientes.services.ProductService;

import java.util.ArrayList;
import java.util.List;

public class ProductController {

  private final ProductService productService;
  private final List<ProductCreatedEventListener> productCreatedEventListeners = new ArrayList<>();

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  public List<Product> getAllProducts() {
    return productService.findAll();
  }

  public void save(Product product) {
    product = productService.save(product);
    emit(product);
  }

  public void subscribe(ProductCreatedEventListener listener) {
    productCreatedEventListeners.add(listener);
  }

  public void emit(Product product) {
    productCreatedEventListeners.forEach(listener -> listener.onProductCreated(product));
  }
}
