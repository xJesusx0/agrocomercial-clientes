package com.agrocomercial.clientes.services.impl.database;

import com.agrocomercial.clientes.models.Administrator;
import com.agrocomercial.clientes.repositories.AdministratorRepository;
import com.agrocomercial.clientes.services.AdministratorService;

import java.util.List;

public class DatabaseAdministratorServiceImpl implements AdministratorService {

    private final AdministratorRepository administratorRepository;

    public DatabaseAdministratorServiceImpl(AdministratorRepository administratorRepository) {
        this.administratorRepository = administratorRepository;
    }

    @Override
    public Administrator findById(Integer id) {
        return administratorRepository.findById(id).orElse(null);
    }

    @Override
    public Administrator findByUserId(Integer userId) {
        return administratorRepository.findByUserId(userId).orElse(null);
    }

    @Override
    public List<Administrator> findAll() {
        return administratorRepository.findAll();
    }
} 