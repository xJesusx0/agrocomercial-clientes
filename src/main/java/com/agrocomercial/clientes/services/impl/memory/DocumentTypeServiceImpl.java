package com.agrocomercial.clientes.services.impl.memory;

import com.agrocomercial.clientes.models.DocumentType;
import com.agrocomercial.clientes.services.DocumentTypeService;

import java.util.List;

@SuppressWarnings("java:S6548")
public class DocumentTypeServiceImpl implements DocumentTypeService {

    List<DocumentType> documentTypes = List.of(
            new DocumentType(1,"CC", "Cedula de ciudadania"),
            new DocumentType(2,"TI", "Tarjeta de identidad")
    );


    @Override
    public List<DocumentType> findAll() {
        return documentTypes;
    }

}
