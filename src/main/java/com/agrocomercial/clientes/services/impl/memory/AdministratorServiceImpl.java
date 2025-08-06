package com.agrocomercial.clientes.services.impl.memory;

import com.agrocomercial.clientes.models.Administrator;
import com.agrocomercial.clientes.services.AdministratorService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class AdministratorServiceImpl implements AdministratorService {
    
    private final List<Administrator> administrators = new ArrayList<>();
    private final AtomicInteger idCounter = new AtomicInteger(1);
    
    public AdministratorServiceImpl() {
        administrators.add(new Administrator(1, "Juan", "Pérez", "3001234567", "12345678", 1, 1, 4));
    }
    

    
    @Override
    public Administrator findById(Integer id) {
        return administrators.stream()
                .filter(admin -> admin.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
    
    @Override
    public Administrator findByUserId(Integer userId) {
        return administrators.stream()
                .filter(admin -> admin.getIdUser().equals(userId))
                .findFirst()
                .orElse(null);
    }
    
    @Override
    public List<Administrator> findAll() {
        return new ArrayList<>(administrators);
    }
    

} 