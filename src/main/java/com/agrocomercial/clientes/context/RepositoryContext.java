package com.agrocomercial.clientes.context;

import com.agrocomercial.clientes.repositories.ProductRepository;
import com.agrocomercial.clientes.repositories.UserRepository;
import com.agrocomercial.clientes.repositories.AdministratorRepository;
import com.agrocomercial.clientes.repositories.LocationRepository;
import com.agrocomercial.clientes.repositories.DocumentTypeRepository;
import com.agrocomercial.clientes.repositories.CustomerRepository;
import com.agrocomercial.clientes.repositories.impl.sqlserver.SqlServerProductRepositoryImpl;
import com.agrocomercial.clientes.repositories.impl.sqlserver.SqlServerUserRepositoryImpl;
import com.agrocomercial.clientes.repositories.impl.sqlserver.SqlServerAdministratorRepositoryImpl;
import com.agrocomercial.clientes.repositories.impl.sqlserver.SqlServerLocationRepositoryImpl;
import com.agrocomercial.clientes.repositories.impl.sqlserver.SqlServerDocumentTypeRepositoryImpl;
import com.agrocomercial.clientes.repositories.impl.sqlserver.SqlServerCustomerRepositoryImpl;

public class RepositoryContext {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final AdministratorRepository administratorRepository;
    private final LocationRepository locationRepository;
    private final DocumentTypeRepository documentTypeRepository;
    private final CustomerRepository customerRepository;

    public RepositoryContext(){
        userRepository = new SqlServerUserRepositoryImpl();
        productRepository = new SqlServerProductRepositoryImpl();
        administratorRepository = new SqlServerAdministratorRepositoryImpl();
        locationRepository = new SqlServerLocationRepositoryImpl();
        documentTypeRepository = new SqlServerDocumentTypeRepositoryImpl();
        customerRepository = new SqlServerCustomerRepositoryImpl();
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public ProductRepository getProductRepository() {
        return productRepository;
    }

    public AdministratorRepository getAdministratorRepository() {
        return administratorRepository;
    }

    public LocationRepository getLocationRepository() {
        return locationRepository;
    }

    public DocumentTypeRepository getDocumentTypeRepository() {
        return documentTypeRepository;
    }

    public CustomerRepository getCustomerRepository() {
        return customerRepository;
    }
}
