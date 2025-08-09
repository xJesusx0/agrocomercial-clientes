package com.agrocomercial.clientes.services.impl.database;

import com.agrocomercial.clientes.models.Order;
import com.agrocomercial.clientes.repositories.OrderRepository;
import com.agrocomercial.clientes.services.OrderService;

import java.util.List;

public class DatabaseOrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public DatabaseOrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public List<Order> findByCustomerId(Integer customerId) {
        return orderRepository.findByCustomerId(customerId);
    }
} 