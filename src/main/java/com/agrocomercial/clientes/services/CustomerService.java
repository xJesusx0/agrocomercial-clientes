package com.agrocomercial.clientes.services;

import com.agrocomercial.clientes.models.Customer;

public interface CustomerService {

  Customer findByUserId(Integer userId);

}
