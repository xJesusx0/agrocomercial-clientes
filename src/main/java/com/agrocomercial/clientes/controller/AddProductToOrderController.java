package com.agrocomercial.clientes.controller;

import com.agrocomercial.clientes.events.OrderProductEventListener;
import com.agrocomercial.clientes.models.*;
import com.agrocomercial.clientes.services.*;

import java.util.ArrayList;
import java.util.List;

public class AddProductToOrderController {

    private final OrderProductService orderProductService;
    private final ProductService productService;
    private final OrderService orderService;
    private final UserService userService;
    private final CustomerService customerService;

    private final List<OrderProductEventListener> listeners = new ArrayList<>();

    private final List<OrderProduct> orderProductListToSave = new ArrayList<>();

    public AddProductToOrderController(OrderProductService orderProductService, ProductService productService, OrderService orderService, UserService userService, CustomerService customerService) {
        this.orderProductService = orderProductService;
        this.productService = productService;
        this.orderService = orderService;
        this.userService = userService;
        this.customerService = customerService;
    }

    public void saveOrder(Long orderNumber){
        Integer customerId = getCustomerId();
        Order order = new Order(orderNumber, customerId);
        order.setIdCustomer(customerId);
        order = orderService.save(order);

        Integer id = order.getId();

        orderProductListToSave.forEach(orderProduct->
                orderProduct.setIdOrder(id)
        );

        orderProductService.saveAll(orderProductListToSave);
    }

    private Integer getCustomerId(){
        User loggedUser = userService.getLoggedUser();
        Customer customer = customerService.findByUserId(loggedUser.getId());
        return customer.getId();
    }

    public void addProductToOrder(Product product, Integer quantity) {
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setQuantity(quantity);
        orderProduct.setIdProduct(product.getId());
        Double subtotal = product.getPrice() * quantity;
        orderProduct.setSubtotal(subtotal);

        orderProductListToSave.add(orderProduct);
        emitOnProductAdded(product, quantity, subtotal);
    }

    public List<Product> getProducts (){
        return productService.findAll();
    }

    public void emitOnProductAdded(Product product, Integer quantity, Double subtotal){
        listeners.forEach(listener -> listener.onProductAdded(product, quantity, subtotal));
    }

    public void subscribe(OrderProductEventListener listener){
        listeners.add(listener);
    }
}


