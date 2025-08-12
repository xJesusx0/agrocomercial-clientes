package com.agrocomercial.clientes.services.impl.memory;

import com.agrocomercial.clientes.models.Order;
import com.agrocomercial.clientes.models.OrderProduct;
import com.agrocomercial.clientes.services.OrderService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.agrocomercial.clientes.utils.ServiceUtils.getLastId;

public class OrderServiceImpl implements OrderService {

    private final List<Order> orderList = new ArrayList<>();

    @Override
    public Order save(Order order) {
        Integer id = getLastId(orderList);
        order.setId(id);
        orderList.add(order);
        return order;
    }

    @Override
    public List<Order> findByCustomerId(Integer customerId) {
        return orderList.stream()
                .filter(order -> order.getIdCustomer().equals(customerId))
                .toList();
    }

    @Override
    public Order createWithProducts(Long orderNumber, Integer customerId, Collection<OrderProduct> products) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createWithProducts'");
    }

}
