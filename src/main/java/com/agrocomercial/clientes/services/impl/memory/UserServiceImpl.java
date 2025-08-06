package com.agrocomercial.clientes.services.impl.memory;

import com.agrocomercial.clientes.models.User;
import com.agrocomercial.clientes.services.UserService;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private final List<User> users = List.of(
            new User(1, "jesus", "1234"),
            new User(2, "daniel", "1234"),
            new User(3, "alberto", "1234")
    );

    @Override
    public User authenticateUser(String username, String password){
        Optional<User> user = users.stream()
                .filter(u ->
                        u.getUsername().equals(username) &&
                        u.getPassword().equals(password))
                .findFirst();

        return user.orElse(null);

    }
}
