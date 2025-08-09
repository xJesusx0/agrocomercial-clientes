package com.agrocomercial.clientes.context;

import com.agrocomercial.clientes.services.*;
import com.agrocomercial.clientes.services.impl.database.DatabaseProductServiceImpl;
import com.agrocomercial.clientes.services.impl.database.DatabaseUserServiceImpl;
import com.agrocomercial.clientes.services.impl.database.DatabaseAdministratorServiceImpl;
import com.agrocomercial.clientes.services.impl.database.DatabaseLocationServiceImpl;
import com.agrocomercial.clientes.services.impl.database.DatabaseDocumentTypeServiceImpl;
import com.agrocomercial.clientes.services.impl.database.DatabaseCustomerServiceImpl;
import com.agrocomercial.clientes.services.impl.memory.*;

public class ServiceContext {

    private final UserService userService;
    private final CustomerService customerService;
    private final DocumentTypeService documentTypeService;
    private final OrderProductService orderProductService;
    private final OrderService orderService;
    private final ProductService productService;
    private final AdministratorService administratorService;
    private final LocationService locationService;

    public ServiceContext() {
        RepositoryContext repositoryContext = new RepositoryContext();

        userService = new DatabaseUserServiceImpl(repositoryContext.getUserRepository());
        customerService = new DatabaseCustomerServiceImpl(repositoryContext.getCustomerRepository());
        documentTypeService = new DatabaseDocumentTypeServiceImpl(repositoryContext.getDocumentTypeRepository());
        orderProductService = new OrderProductServiceImpl();
        orderService = new OrderServiceImpl();
        productService = new DatabaseProductServiceImpl(repositoryContext.getProductRepository());
        administratorService = new DatabaseAdministratorServiceImpl(repositoryContext.getAdministratorRepository());
        locationService = new DatabaseLocationServiceImpl(repositoryContext.getLocationRepository());
    }

    public UserService getUserService() {
        return userService;
    }

    public CustomerService getCustomerService() {
        return customerService;
    }

    public DocumentTypeService getDocumentTypeService() {
        return documentTypeService;
    }

    public OrderProductService getOrderProductService() {
        return orderProductService;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public ProductService getProductService() {
        return productService;
    }

    public AdministratorService getAdministratorService() {
        return administratorService;
    }

    public LocationService getLocationService() {
        return locationService;
    }
}
