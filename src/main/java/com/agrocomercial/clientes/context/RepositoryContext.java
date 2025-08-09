package com.agrocomercial.clientes.context;

import com.agrocomercial.clientes.repositories.ProductRepository;
import com.agrocomercial.clientes.repositories.UserRepository;
import com.agrocomercial.clientes.repositories.impl.sqlserver.SqlServerProductRepositoryImpl;
import com.agrocomercial.clientes.repositories.impl.sqlserver.SqlServerUserRepositoryImpl;

public class RepositoryContext {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public RepositoryContext(){
        userRepository = new SqlServerUserRepositoryImpl();
        productRepository = new SqlServerProductRepositoryImpl();
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public ProductRepository getProductRepository() {
        return productRepository;
    }
}
