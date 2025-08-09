package com.agrocomercial.clientes.services.impl.database;

import com.agrocomercial.clientes.models.DocumentType;
import com.agrocomercial.clientes.repositories.DocumentTypeRepository;
import com.agrocomercial.clientes.services.DocumentTypeService;

import java.util.List;

public class DatabaseDocumentTypeServiceImpl implements DocumentTypeService {

    private final DocumentTypeRepository documentTypeRepository;

    public DatabaseDocumentTypeServiceImpl(DocumentTypeRepository documentTypeRepository) {
        this.documentTypeRepository = documentTypeRepository;
    }

    @Override
    public List<DocumentType> findAll() {
        return documentTypeRepository.findAll();
    }
} 