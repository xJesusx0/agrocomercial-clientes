package com.agrocomercial.clientes.repositories;

import com.agrocomercial.clientes.models.User;

import java.util.Optional;

public interface UserRepository {

  Optional<User> findByUsernameAndPassword(String username, String password);

}
