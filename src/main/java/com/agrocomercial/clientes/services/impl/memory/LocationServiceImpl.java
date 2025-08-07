package com.agrocomercial.clientes.services.impl.memory;

import com.agrocomercial.clientes.models.Location;
import com.agrocomercial.clientes.services.LocationService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class LocationServiceImpl implements LocationService {
    
    private final List<Location> locations = new ArrayList<>();
    private final AtomicInteger idCounter = new AtomicInteger(1);
    
    public LocationServiceImpl() {
        locations.add(new Location(1, "Sucursal Centro", "Calle 34 #43-50, Centro Histórico, Barranquilla"));
        locations.add(new Location(2, "Sucursal Norte", "Carrera 51B #82-254, Alto Prado, Barranquilla"));
        locations.add(new Location(3, "Sucursal Sur", "Calle 47 #21-38, Barrio Abajo, Barranquilla"));
    }
    
    @Override
    public Location findById(Integer id) {
        return locations.stream()
                .filter(loc -> loc.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
    
    @Override
    public List<Location> findAll() {
        return new ArrayList<>(locations);
    }
    

} 