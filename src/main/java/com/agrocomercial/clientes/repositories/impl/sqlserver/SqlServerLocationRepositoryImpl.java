package com.agrocomercial.clientes.repositories.impl.sqlserver;

import com.agrocomercial.clientes.database.DatabaseOperation;
import com.agrocomercial.clientes.database.DatabaseOperationHandler;
import com.agrocomercial.clientes.models.Location;
import com.agrocomercial.clientes.repositories.LocationRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class SqlServerLocationRepositoryImpl implements LocationRepository {
    @Override
    public Optional<Location> findById(Integer id) {
        AtomicReference<Location> item = new AtomicReference<>();
        DatabaseOperation op = connection -> {
            final String query = "SELECT * FROM sucursales WHERE id_sucursal = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                item.set(mapRow(rs));
            }
        };
        DatabaseOperationHandler.handleOperation(op);
        return Optional.ofNullable(item.get());
    }

    @Override
    public List<Location> findAll() {
        List<Location> list = new ArrayList<>();
        DatabaseOperation op = connection -> {
            final String query = "SELECT * FROM sucursales";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        };
        DatabaseOperationHandler.handleOperation(op);
        return list;
    }

    private Location mapRow(ResultSet rs) throws java.sql.SQLException {
        Integer id = rs.getInt("id_sucursal");
        String name = rs.getString("nombre_sucursal");
        String address = rs.getString("direccion");
        return new Location(id, name, address);
    }
} 