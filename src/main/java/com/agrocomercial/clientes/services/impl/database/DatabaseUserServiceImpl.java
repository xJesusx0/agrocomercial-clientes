package com.agrocomercial.clientes.services.impl.database;

import com.agrocomercial.clientes.models.User;
import com.agrocomercial.clientes.repositories.UserRepository;
import com.agrocomercial.clientes.services.UserService;

public class DatabaseUserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public DatabaseUserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User authenticateUser(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password)
                .orElse(null);
    }
}
