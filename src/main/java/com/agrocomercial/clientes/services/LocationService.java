package com.agrocomercial.clientes.services;

import com.agrocomercial.clientes.models.Location;

import java.util.List;

public interface LocationService {
    Location findById(Integer id);
    List<Location> findAll();
} 