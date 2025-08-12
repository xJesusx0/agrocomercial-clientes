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
            final String sql = "INSERT INTO pedidos_productos (id_pedido, id_producto, cantidad) VALUES (?, ?, ?);";
            PreparedStatement ps = connection.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, orderProduct.getIdOrder());
            ps.setInt(2, orderProduct.getIdProduct());
            ps.setInt(3, orderProduct.getQuantity() != null ? orderProduct.getQuantity() : 1);
            ps.executeUpdate();
            try (var rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    orderProduct.setId(rs.getInt(1));
                }
            }
        };
        DatabaseOperationHandler.handleOperation(op);
        return orderProduct;
    }

    @Override
    public void saveAll(Collection<OrderProduct> orderProducts) {
        DatabaseOperation op = connection -> {
            final String sql = "INSERT INTO pedidos_productos (id_pedido, id_producto, cantidad) VALUES (?, ?, ?);";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                for (OrderProduct opi : orderProducts) {
                    ps.setInt(1, opi.getIdOrder());
                    ps.setInt(2, opi.getIdProduct());
                    ps.setInt(3, opi.getQuantity() != null ? opi.getQuantity() : 1);
                    ps.addBatch();
                }
                ps.executeBatch();
            }
        };
        DatabaseOperationHandler.handleOperation(op);
    }
} 