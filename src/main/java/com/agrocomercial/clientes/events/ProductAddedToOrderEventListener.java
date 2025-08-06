package com.agrocomercial.clientes.events;

import com.agrocomercial.clientes.models.Product;

public interface ProductAddedToOrderEventListener {
    void onProductAdded(Product product, Integer quantity, Double subtotal);
}
