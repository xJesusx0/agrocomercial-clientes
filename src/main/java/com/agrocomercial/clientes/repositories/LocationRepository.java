package com.agrocomercial.clientes.repositories;

import com.agrocomercial.clientes.models.Location;

import java.util.List;
import java.util.Optional;

public interface LocationRepository {
    Optional<Location> findById(Integer id);
    List<Location> findAll();
} 