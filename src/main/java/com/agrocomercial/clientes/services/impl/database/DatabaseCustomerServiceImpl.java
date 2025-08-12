package com.agrocomercial.clientes.services.impl.database;

import com.agrocomercial.clientes.models.Customer;
import com.agrocomercial.clientes.repositories.CustomerRepository;
import com.agrocomercial.clientes.services.CustomerService;

public class DatabaseCustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public DatabaseCustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer findByUserId(Integer userId) {
        return customerRepository.findByUserId(userId).orElse(null);
    }
} 