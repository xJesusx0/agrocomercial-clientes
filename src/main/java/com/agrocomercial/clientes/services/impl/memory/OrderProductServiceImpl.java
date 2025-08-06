package com.agrocomercial.clientes.services.impl.memory;

import com.agrocomercial.clientes.models.OrderProduct;
import com.agrocomercial.clientes.services.OrderProductService;
import com.agrocomercial.clientes.utils.ServiceUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class OrderProductServiceImpl implements OrderProductService {

  private final List<OrderProduct> orderProductList = new ArrayList<>();

  public List<OrderProduct> findAll() {
    return orderProductList;
  }

  public List<OrderProduct> findAllByOrderId(int orderId) {
    return orderProductList.stream().filter(op -> op.getIdOrder() == orderId).toList();
  }

  @Override
  public OrderProduct save(OrderProduct orderProduct) {
    Integer id = ServiceUtils.getLastId(orderProductList);

    orderProduct.setId(id);
    orderProductList.add(orderProduct);
    return orderProduct;
  }

  @Override
  public void saveAll(Collection<OrderProduct> orderProductList) {
    orderProductList.forEach(this::save);
  }

}
