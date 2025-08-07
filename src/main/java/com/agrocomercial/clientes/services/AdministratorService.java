package com.agrocomercial.clientes.services;

import com.agrocomercial.clientes.models.Administrator;

import java.util.List;

public interface AdministratorService {
    Administrator findById(Integer id);
    Administrator findByUserId(Integer userId);
    List<Administrator> findAll();
} 