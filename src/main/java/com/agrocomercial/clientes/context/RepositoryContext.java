package com.agrocomercial.clientes.context;

import com.agrocomercial.clientes.repositories.UserRepository;
import com.agrocomercial.clientes.repositories.impl.UserRepositoryImpl;

public class RepositoryContext {

  private final UserRepositoryImpl userRepository;

  public RepositoryContext() {
    userRepository = new UserRepositoryImpl();
  }

  public UserRepository getUserRepository() {
    return userRepository;
  }

}
