package com.agrocomercial.clientes.repositories;

import com.agrocomercial.clientes.models.Administrator;

import java.util.List;
import java.util.Optional;

public interface AdministratorRepository {
    Optional<Administrator> findById(Integer id);
    Optional<Administrator> findByUserId(Integer userId);
    List<Administrator> findAll();
} 