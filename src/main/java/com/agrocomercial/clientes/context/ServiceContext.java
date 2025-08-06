package com.agrocomercial.clientes.context;

import com.agrocomercial.clientes.services.CustomerService;
import com.agrocomercial.clientes.services.UserService;
import com.agrocomercial.clientes.services.impl.memory.CustomerServiceImpl;
import com.agrocomercial.clientes.services.impl.database.DatabaseUserServiceImpl;

public class ServiceContext {

    private final DatabaseUserServiceImpl userService;
    private final CustomerServiceImpl customerService;

    public ServiceContext() {
        RepositoryContext repositoryContext = new RepositoryContext();

        userService = new DatabaseUserServiceImpl(repositoryContext.getUserRepository());
        customerService = new CustomerServiceImpl();
    }

    public UserService getUserService() {
        return userService;
    }

    public CustomerService getCustomerService() {
        return customerService;
    }

}
