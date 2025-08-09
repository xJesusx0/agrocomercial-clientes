package com.agrocomercial.clientes.repositories.impl.sqlserver;

import com.agrocomercial.clientes.database.DatabaseOperation;
import com.agrocomercial.clientes.database.DatabaseOperationHandler;
import com.agrocomercial.clientes.models.OrderProduct;
import com.agrocomercial.clientes.repositories.OrderProductRepository;

import java.sql.PreparedStatement;
import java.util.Collection;

public class SqlServerOrderProductRepositoryImpl implements OrderProductRepository {
    @Override
    public OrderProduct save(OrderProduct orderProduct) {
        DatabaseOperation op = connection -> {
            final String sql = "INSERT INTO pedidos_productos (id_pedido, id_producto) VALUES (?, ?);";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                int times = Math.max(1, orderProduct.getQuantity() == null ? 1 : orderProduct.getQuantity());
                for (int i = 0; i < times; i++) {
                    ps.setInt(1, orderProduct.getIdOrder());
                    ps.setInt(2, orderProduct.getIdProduct());
                    ps.addBatch();
                }
                ps.executeBatch();
            }
        };
        DatabaseOperationHandler.handleOperation(op);
        return orderProduct;
    }

    @Override
    public void saveAll(Collection<OrderProduct> orderProducts) {
        DatabaseOperation op = connection -> {
            final String sql = "INSERT INTO pedidos_productos (id_pedido, id_producto) VALUES (?, ?);";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                for (OrderProduct opi : orderProducts) {
                    int times = Math.max(1, opi.getQuantity() == null ? 1 : opi.getQuantity());
                    for (int i = 0; i < times; i++) {
                        ps.setInt(1, opi.getIdOrder());
                        ps.setInt(2, opi.getIdProduct());
                        ps.addBatch();
                    }
                }
                ps.executeBatch();
            }
        };
        DatabaseOperationHandler.handleOperation(op);
    }
} 