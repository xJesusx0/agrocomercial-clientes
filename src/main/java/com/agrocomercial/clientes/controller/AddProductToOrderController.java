package com.agrocomercial.clientes.controller;

import com.agrocomercial.clientes.events.OrderProductEventListener;
import com.agrocomercial.clientes.models.OrderProduct;
import com.agrocomercial.clientes.models.Product;
import com.agrocomercial.clientes.services.OrderProductService;
import com.agrocomercial.clientes.services.OrderService;
import com.agrocomercial.clientes.services.ProductService;

import java.util.ArrayList;
import java.util.List;

public class AddProductToOrderController {

    private final OrderProductService orderProductService;
    private final ProductService productService;
    private final OrderService orderService;

    private final List<OrderProductEventListener> listeners = new ArrayList<>();

    private final List<OrderProduct> orderProductListToSave = new ArrayList<>();

    public AddProductToOrderController(OrderProductService orderProductService, ProductService productService, OrderService orderService) {
        this.orderProductService = orderProductService;
        this.productService = productService;
        this.orderService = orderService;
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


