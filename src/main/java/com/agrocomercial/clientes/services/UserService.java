package com.agrocomercial.clientes.services;

import com.agrocomercial.clientes.models.User;

import java.util.List;

public interface UserService {

    User authenticateUser (String username, String password);
    List<User> findAll();

}
