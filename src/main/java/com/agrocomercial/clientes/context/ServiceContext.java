package com.agrocomercial.clientes.context;

import com.agrocomercial.clientes.services.*;
import com.agrocomercial.clientes.services.impl.memory.*;
import com.agrocomercial.clientes.services.impl.database.DatabaseUserServiceImpl;

public class ServiceContext {

  private final UserService userService;
  private final CustomerService customerService;
  private final DocumentTypeService documentTypeService;
  private final OrderProductService orderProductService;
  private final OrderService orderService;
  private final ProductService productService;

  public ServiceContext() {
    RepositoryContext repositoryContext = new RepositoryContext();

    userService = new DatabaseUserServiceImpl(repositoryContext.getUserRepository());
    customerService = new CustomerServiceImpl();
    documentTypeService = new DocumentTypeServiceImpl();
    orderProductService = new OrderProductServiceImpl();
    orderService = new OrderServiceImpl();
    productService = new ProductServiceImpl();
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
}
