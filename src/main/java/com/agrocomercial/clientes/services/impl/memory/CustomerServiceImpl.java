package com.agrocomercial.clientes.services.impl.memory;

import com.agrocomercial.clientes.models.Customer;
import com.agrocomercial.clientes.services.CustomerService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.agrocomercial.clientes.utils.ServiceUtils.getLastId;

public class CustomerServiceImpl implements CustomerService {

  private final List<Customer> customers = new ArrayList<>();

  public CustomerServiceImpl() {
    customers.add(new Customer(1, "Jesus", "Perea", "3011234567", "1041231223", 1, 1));
  }

  public List<Customer> findAll() {
    return customers;
  }

  public Customer save(Customer customer) {
    Integer id = getLastId(this.customers);
    customer.setId(id);

    customers.add(customer);
    return customer;
  }

  @Override
  public Customer findByUserId(Integer userId) {
    Optional<Customer> customer = customers.stream().filter(c -> Objects.equals(c.getIdUser(), userId)).findFirst();

    return customer.orElse(null);
  }
}
