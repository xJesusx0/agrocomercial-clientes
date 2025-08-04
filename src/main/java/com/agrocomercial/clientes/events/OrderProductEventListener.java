package com.agrocomercial.clientes.events;

import com.agrocomercial.clientes.models.Product;

public interface OrderProductEventListener {
    void onProductAdded(Product product, Integer quantity, Double subtotal);
}
