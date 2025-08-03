package com.agrocomercial.clientes.services;

import com.agrocomercial.clientes.models.OrderProduct;

import java.util.ArrayList;
import java.util.List;

public class OrderProductService {

    private final List<OrderProduct> orderProductList = new ArrayList<>();

    public List<OrderProduct> findAll() {
        return orderProductList;
    }

    public List<OrderProduct> findAllByOrderId(int orderId) {
        return orderProductList.stream()
                .filter(op -> op.getIdOrder() == orderId)
                .toList();
    }

}
