package com.agrocomercial.clientes.repositories;

import com.agrocomercial.clientes.models.DocumentType;

import java.util.List;

public interface DocumentTypeRepository {
    List<DocumentType> findAll();
} 