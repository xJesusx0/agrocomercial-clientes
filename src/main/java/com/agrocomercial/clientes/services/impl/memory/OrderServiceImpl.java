package com.agrocomercial.clientes.services.impl.memory;

import com.agrocomercial.clientes.models.Order;

import java.util.ArrayList;
import java.util.List;

import static com.agrocomercial.clientes.utils.ServiceUtils.getLastId;

public class OrderServiceImpl {

    private final List<Order> orderList = new ArrayList<>();

    public Order save(Order order) {
        Integer id = getLastId(orderList);
        order.setId(id);
        orderList.add(order);
        return order;
    }

    public List<Order> findByCustomerId(Integer customerId) {
        return orderList.stream()
                .filter(order -> order.getIdCustomer().equals(customerId))
                .toList();
    }

}
