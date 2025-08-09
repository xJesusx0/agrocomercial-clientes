package com.agrocomercial.clientes.services.impl.database;

import com.agrocomercial.clientes.models.Location;
import com.agrocomercial.clientes.repositories.LocationRepository;
import com.agrocomercial.clientes.services.LocationService;

import java.util.List;

public class DatabaseLocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    public DatabaseLocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public Location findById(Integer id) {
        return locationRepository.findById(id).orElse(null);
    }

    @Override
    public List<Location> findAll() {
        return locationRepository.findAll();
    }
} 