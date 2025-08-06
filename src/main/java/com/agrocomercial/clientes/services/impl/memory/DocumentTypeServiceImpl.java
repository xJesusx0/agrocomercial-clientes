package com.agrocomercial.clientes.services.impl.memory;

import com.agrocomercial.clientes.models.DocumentType;

import java.util.List;

@SuppressWarnings("java:S6548")
public class DocumentTypeServiceImpl {

    List<DocumentType> documentTypes = List.of(
            new DocumentType(1,"CC", "Cedula de ciudadania"),
            new DocumentType(2,"TI", "Tarjeta de identidad")
    );


    public List<DocumentType> findAll() {
        return documentTypes;
    }

}
