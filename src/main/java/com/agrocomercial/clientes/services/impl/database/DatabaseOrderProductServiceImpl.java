package com.agrocomercial.clientes.services.impl.database;

import com.agrocomercial.clientes.models.OrderProduct;
import com.agrocomercial.clientes.repositories.OrderProductRepository;
import com.agrocomercial.clientes.services.OrderProductService;

import java.util.Collection;

public class DatabaseOrderProductServiceImpl implements OrderProductService {

    private final OrderProductRepository orderProductRepository;

    public DatabaseOrderProductServiceImpl(OrderProductRepository orderProductRepository) {
        this.orderProductRepository = orderProductRepository;
    }

    @Override
    public OrderProduct save(OrderProduct orderProduct) {
        return orderProductRepository.save(orderProduct);
    }

    @Override
    public void saveAll(Collection<OrderProduct> orderProducts) {
        orderProductRepository.saveAll(orderProducts);
    }
} 