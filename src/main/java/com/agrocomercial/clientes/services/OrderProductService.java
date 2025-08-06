package com.agrocomercial.clientes.services;

import com.agrocomercial.clientes.models.OrderProduct;

import java.util.Collection;

public interface OrderProductService {

    OrderProduct save(OrderProduct orderProduct);
    void saveAll(Collection<OrderProduct> orderProducts);
}
