package com.agrocomercial.clientes.controller.administrators;

import com.agrocomercial.clientes.models.Administrator;
import com.agrocomercial.clientes.services.AdministratorService;

import java.util.List;

public class AdministratorController {
    
    private final AdministratorService administratorService;
    
    public AdministratorController(AdministratorService administratorService) {
        this.administratorService = administratorService;
    }
    
    public Administrator findById(Integer id) {
        return administratorService.findById(id);
    }
    
    public Administrator findByUserId(Integer userId) {
        return administratorService.findByUserId(userId);
    }
    
    public List<Administrator> findAll() {
        return administratorService.findAll();
    }
    
} 