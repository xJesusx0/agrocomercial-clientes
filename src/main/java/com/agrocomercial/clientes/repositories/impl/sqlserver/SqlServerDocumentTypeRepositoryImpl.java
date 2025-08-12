package com.agrocomercial.clientes.repositories.impl.sqlserver;

import com.agrocomercial.clientes.database.DatabaseOperation;
import com.agrocomercial.clientes.database.DatabaseOperationHandler;
import com.agrocomercial.clientes.models.DocumentType;
import com.agrocomercial.clientes.repositories.DocumentTypeRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SqlServerDocumentTypeRepositoryImpl implements DocumentTypeRepository {
    @Override
    public List<DocumentType> findAll() {
        List<DocumentType> list = new ArrayList<>();
        DatabaseOperation op = connection -> {
            final String query = "SELECT * FROM tipos_documento";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt("id_tipo_documento");
                String code = rs.getString("codigo");
                String name = rs.getString("nombre_tipo_documento");
                list.add(new DocumentType(id, code, name));
            }
        };
        DatabaseOperationHandler.handleOperation(op);
        return list;
    }
} 