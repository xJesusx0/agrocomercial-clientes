package com.agrocomercial.clientes.services;

import static com.agrocomercial.clientes.utils.ServiceUtils.getLastId;

import com.agrocomercial.clientes.models.Customer;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerService {

  private final List<Customer> customers = new ArrayList<>();

  public CustomerService() {
    customers.add(new Customer(1, "Jesus", "Perea", "3011234567", 1, 1));
  }

  public List<Customer> findAll() {
    return customers;
  }

  public Customer findByUserId(int id) {
    Optional<Customer> customer = customers.stream().filter(c -> c.getIdUser() == id).findFirst();

    return customer.orElse(null);
  }

  public Customer save(Customer customer) {
    Integer id = getLastId(this.customers);
    customer.setId(id);

    customers.add(customer);
    return customer;
  }

}
