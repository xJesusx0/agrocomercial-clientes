package com.agrocomercial.clientes.services;

import com.agrocomercial.clientes.models.User;

public interface UserService {

  User authenticateUser(String username, String password);

}
