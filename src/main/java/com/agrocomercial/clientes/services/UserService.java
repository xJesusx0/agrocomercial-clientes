package com.agrocomercial.clientes.services;

import com.agrocomercial.clientes.models.User;

import java.util.List;
import java.util.Optional;

public class UserService {

  private final List<User> users =
          List.of(new User(1, "jesus", "1234"), new User(2, "daniel", "1234"), new User(3, "alberto", "1234"));

  public User authenticateUser(String username, String password) {
    Optional<User> user = users.stream()
            .filter(u -> u.getUsername().equals(username) && u.getPassword().equals(password)).findFirst();

    if (user.isPresent()) {
      return user.get();
    }

    return null;
  }
}
