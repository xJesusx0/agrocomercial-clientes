package com.agrocomercial.clientes.repositories.impl.sqlserver;

import com.agrocomercial.clientes.database.DatabaseOperation;
import com.agrocomercial.clientes.database.DatabaseOperationHandler;
import com.agrocomercial.clientes.models.Administrator;
import com.agrocomercial.clientes.repositories.AdministratorRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class SqlServerAdministratorRepositoryImpl implements AdministratorRepository {
    @Override
    public Optional<Administrator> findById(Integer id) {
        AtomicReference<Administrator> admin = new AtomicReference<>();
        DatabaseOperation op = connection -> {
            final String query = "SELECT * FROM administradores WHERE id_administrador = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                admin.set(mapRow(rs));
            }
        };
        DatabaseOperationHandler.handleOperation(op);
        return Optional.ofNullable(admin.get());
    }

    @Override
    public Optional<Administrator> findByUserId(Integer userId) {
        AtomicReference<Administrator> admin = new AtomicReference<>();
        DatabaseOperation op = connection -> {
            final String query = "SELECT * FROM administradores WHERE id_usuario = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                admin.set(mapRow(rs));
            }
        };
        DatabaseOperationHandler.handleOperation(op);
        return Optional.ofNullable(admin.get());
    }

    @Override
    public List<Administrator> findAll() {
        List<Administrator> list = new ArrayList<>();
        DatabaseOperation op = connection -> {
            final String query = "SELECT * FROM administradores";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        };
        DatabaseOperationHandler.handleOperation(op);
        return list;
    }

    private Administrator mapRow(ResultSet rs) throws java.sql.SQLException {
        Integer id = rs.getInt("id_administrador");
        String name = rs.getString("nombre_administrador");
        String lastname = rs.getString("apellido_administrador");
        String phone = rs.getString("telefono");
        String identification = rs.getString("numero_documento");
        Integer idDocumentType = rs.getInt("id_tipo_documento");
        Integer idLocation = rs.getInt("id_sucursal");
        Integer idUser = rs.getInt("id_usuario");
        return new Administrator(id, name, lastname, phone, identification, idDocumentType, idLocation, idUser);
    }
} 