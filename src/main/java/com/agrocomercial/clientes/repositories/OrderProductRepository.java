package com.agrocomercial.clientes.repositories;

import com.agrocomercial.clientes.models.OrderProduct;

import java.util.Collection;

public interface OrderProductRepository {
    OrderProduct save(OrderProduct orderProduct);
    void saveAll(Collection<OrderProduct> orderProducts);
} 