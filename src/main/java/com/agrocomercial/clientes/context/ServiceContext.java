package com.agrocomercial.clientes.context;

import com.agrocomercial.clientes.services.UserService;
import com.agrocomercial.clientes.services.impl.DatabaseUserServiceImpl;

public class ServiceContext {

    private final DatabaseUserServiceImpl userService;

    public ServiceContext() {
        RepositoryContext repositoryContext = new RepositoryContext();

        userService = new DatabaseUserServiceImpl(repositoryContext.getUserRepository());
    }

    public UserService getUserService() {
        return userService;
    }

}
