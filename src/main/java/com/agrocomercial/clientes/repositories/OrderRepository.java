package com.agrocomercial.clientes.repositories;

import com.agrocomercial.clientes.models.Order;

import java.util.List;

public interface OrderRepository {
    Order save(Order order);
    List<Order> findByCustomerId(Integer customerId);
} 