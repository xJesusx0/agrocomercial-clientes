package com.agrocomercial.clientes.services;

import com.agrocomercial.clientes.models.DocumentType;

import java.util.List;

@SuppressWarnings("java:S6548")
public class DocumentTypeService {

    List<DocumentType> documentTypes = List.of(
            new DocumentType(1,"CC", "Cedula de ciudadania"),
            new DocumentType(2,"TI", "Tarjeta de identidad")
    );


    public List<DocumentType> findAll() {
        return documentTypes;
    }

}
