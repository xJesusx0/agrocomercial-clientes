package com.agrocomercial.clientes.repositories.impl.sqlserver;

import com.agrocomercial.clientes.database.DatabaseOperation;
import com.agrocomercial.clientes.database.DatabaseOperationHandler;
import com.agrocomercial.clientes.models.Order;
import com.agrocomercial.clientes.repositories.OrderRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SqlServerOrderRepositoryImpl implements OrderRepository {
    @Override
    public Order save(Order order) {
        DatabaseOperation op = connection -> {
            final String sql = "INSERT INTO pedidos (numero_orden, id_comprador) VALUES (?, ?);";
            PreparedStatement ps = connection.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, order.getOrderNumber());
            ps.setInt(2, order.getIdCustomer());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                order.setId(rs.getInt(1));
            }
        };
        DatabaseOperationHandler.handleOperation(op);
        return order;
    }

    @Override
    public List<Order> findByCustomerId(Integer customerId) {
        List<Order> list = new ArrayList<>();
        DatabaseOperation op = connection -> {
            final String sql =
                    "SELECT p.id_pedido, p.numero_orden, p.id_comprador, SUM(pr.precio) AS subtotal " +
                    "FROM pedidos p " +
                    "LEFT JOIN pedidos_productos pp ON pp.id_pedido = p.id_pedido " +
                    "LEFT JOIN productos pr ON pr.id_producto = pp.id_producto " +
                    "WHERE p.id_comprador = ? " +
                    "GROUP BY p.id_pedido, p.numero_orden, p.id_comprador";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, customerId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt("id_pedido");
                Long orderNumber = rs.getLong("numero_orden");
                Integer idCustomer = rs.getInt("id_comprador");
                Double subtotal = rs.getDouble("subtotal");
                if (rs.wasNull()) subtotal = 0.0;
                list.add(new Order(id, orderNumber, idCustomer, subtotal));
            }
        };
        DatabaseOperationHandler.handleOperation(op);
        return list;
    }
} 