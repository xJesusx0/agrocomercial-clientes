package com.agrocomercial.clientes.repositories;

import com.agrocomercial.clientes.models.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {
	Optional<Customer> findByUserId(Integer userId);
	List<Customer> findAll();
}
