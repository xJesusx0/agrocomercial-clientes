package com.agrocomercial.clientes.context;

import com.agrocomercial.clientes.repositories.UserRepository;
import com.agrocomercial.clientes.repositories.impl.sqlserver.SqlServerUserRepositoryImpl;

public class RepositoryContext {

    private final UserRepository userRepository;

    public RepositoryContext(){
        userRepository = new SqlServerUserRepositoryImpl();
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

}
