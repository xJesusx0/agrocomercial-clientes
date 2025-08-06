package com.agrocomercial.clientes.events;

import com.agrocomercial.clientes.models.Product;

public interface ProductCreatedEventListener {
  void onProductCreated(Product product);
}
