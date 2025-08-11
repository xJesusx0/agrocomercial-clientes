package com.agrocomercial.clientes.controller.orders;

import com.agrocomercial.clientes.controller.auth.LoggedUser;
import com.agrocomercial.clientes.events.OrderCreatedEventListener;
import com.agrocomercial.clientes.events.ProductAddedToOrderEventListener;
import com.agrocomercial.clientes.models.*;
import com.agrocomercial.clientes.services.OrderProductService;
import com.agrocomercial.clientes.services.OrderService;
import com.agrocomercial.clientes.services.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class OrderProductController {

    private final OrderProductService orderProductService;
    private final ProductService productService;
    private final OrderService orderService;

    private final List<ProductAddedToOrderEventListener> productAddedToOrderEventListeners = new ArrayList<>();
    private final List<OrderCreatedEventListener> orderCreatedEventListeners = new ArrayList<>();

    private final List<OrderProduct> orderProductListToSave = new ArrayList<>();

    private final LoggedUser loggedUser;

    public OrderProductController(OrderProductService orderProductService, ProductService productService, OrderService orderService, LoggedUser loggedUser) {
        this.orderProductService = orderProductService;
        this.productService = productService;
        this.orderService = orderService;
        this.loggedUser = loggedUser;
    }

    public void saveOrder(Long orderNumber){
        Integer customerId = getCustomerId();
        
        // Use the new transactional method
        Order order = orderService.createWithProducts(orderNumber, customerId, orderProductListToSave);
        
        emitOnOrderCreated(order);
        
        // Clear the list after successful save
        orderProductListToSave.clear();
    }

    private Integer getCustomerId(){
        return loggedUser.getCustomerId();
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

    public List<Order> getOrders(){
        Integer customerId = getCustomerId();
        return orderService.findByCustomerId(customerId);
    }

    public void emitOnProductAdded(Product product, Integer quantity, Double subtotal){
        productAddedToOrderEventListeners.forEach(listener -> listener.onProductAdded(product, quantity, subtotal));
    }

    public void emitOnOrderCreated(Order order){
        orderCreatedEventListeners.forEach(listener -> listener.onOrderCreated(order));
    }

    public void subscribeToProductAddedToOrder(ProductAddedToOrderEventListener listener){
        productAddedToOrderEventListeners.add(listener);
    }

    public void subscribeToOrderCreated(OrderCreatedEventListener listener){
        orderCreatedEventListeners.add(listener);
    }
}


