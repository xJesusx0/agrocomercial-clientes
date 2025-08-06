package com.agrocomercial.clientes.services;

import com.agrocomercial.clientes.models.Order;

import java.util.List;

public interface OrderService {

  Order save(Order order);

  List<Order> findByCustomerId(Integer customerId);
}
