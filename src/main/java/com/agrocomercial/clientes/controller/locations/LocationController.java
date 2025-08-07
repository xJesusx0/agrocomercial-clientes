package com.agrocomercial.clientes.controller.locations;

import com.agrocomercial.clientes.models.Location;
import com.agrocomercial.clientes.services.LocationService;

import java.util.List;

public class LocationController {
    
    private final LocationService locationService;
    
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }
    

    
    public Location findById(Integer id) {
        return locationService.findById(id);
    }
    
    public List<Location> findAll() {
        return locationService.findAll();
    }
    

    
    public Location findByName(String name) {
        return locationService.findAll().stream()
                .filter(loc -> loc.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
} 