package com.agrocomercial.clientes.services.impl.database;

import com.agrocomercial.clientes.models.User;
import com.agrocomercial.clientes.repositories.UserRepository;
import com.agrocomercial.clientes.services.UserService;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
