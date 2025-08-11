package com.agrocomercial.clientes.services.impl.database;

import com.agrocomercial.clientes.database.DatabaseOperation;
import com.agrocomercial.clientes.database.DatabaseOperationHandler;
import com.agrocomercial.clientes.models.Order;
import com.agrocomercial.clientes.models.OrderProduct;
import com.agrocomercial.clientes.repositories.OrderRepository;
import com.agrocomercial.clientes.services.OrderService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
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

    @Override
    public Order createWithProducts(Long orderNumber, Integer customerId, Collection<OrderProduct> products) {
        Order order = new Order(orderNumber, customerId, 0.0); // subtotal will be calculated later
        
        DatabaseOperation op = connection -> {
            // 1. Insert order and get generated ID
            final String insertOrderSql = "INSERT INTO pedidos (numero_orden, id_comprador) VALUES (?, ?);";
            PreparedStatement psOrder = connection.prepareStatement(insertOrderSql, java.sql.Statement.RETURN_GENERATED_KEYS);
            psOrder.setLong(1, orderNumber);
            psOrder.setInt(2, customerId);
            psOrder.executeUpdate();
            
            ResultSet rs = psOrder.getGeneratedKeys();
            if (rs.next()) {
                order.setId(rs.getInt(1));
            }
            
            // 2. Insert all order products in batch
            if (!products.isEmpty()) {
                final String insertProductsSql = "INSERT INTO pedidos_productos (id_pedido, id_producto, cantidad) VALUES (?, ?, ?);";
                PreparedStatement psProducts = connection.prepareStatement(insertProductsSql);
                
                for (OrderProduct product : products) {
                    product.setIdOrder(order.getId());
                    psProducts.setInt(1, order.getId());
                    psProducts.setInt(2, product.getIdProduct());
                    psProducts.setInt(3, product.getQuantity() != null ? product.getQuantity() : 1);
                    psProducts.addBatch();
                }
                psProducts.executeBatch();
            }
        };
        
        DatabaseOperationHandler.handleOperation(op);
        return order;
    }
} 