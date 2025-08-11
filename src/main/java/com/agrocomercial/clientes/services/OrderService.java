package com.agrocomercial.clientes.services;

import com.agrocomercial.clientes.models.Order;
import com.agrocomercial.clientes.models.OrderProduct;

import java.util.Collection;
import java.util.List;

public interface OrderService {

    Order save(Order order);
    List<Order> findByCustomerId(Integer customerId);
    Order createWithProducts(Long orderNumber, Integer customerId, Collection<OrderProduct> products);
}
