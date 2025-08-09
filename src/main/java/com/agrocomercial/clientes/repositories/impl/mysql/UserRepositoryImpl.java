package com.agrocomercial.clientes.repositories.impl.mysql;

import com.agrocomercial.clientes.database.DatabaseOperation;
import com.agrocomercial.clientes.database.DatabaseOperationHandler;
import com.agrocomercial.clientes.models.User;
import com.agrocomercial.clientes.repositories.UserRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class UserRepositoryImpl implements UserRepository {


    @Override
    public Optional<User> findByUsernameAndPassword(String username, String password) {
        AtomicReference<User> user = new AtomicReference<>();
        DatabaseOperation findUserByUsernameAndPassword = connection -> {
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Integer storedId = resultSet.getInt("id");
                String storedUsername = resultSet.getString("username");
                String storedPassword = resultSet.getString("password");

                user.set(new User(storedId, storedUsername, storedPassword));
            }
        };

        DatabaseOperationHandler.handleOperation(findUserByUsernameAndPassword);

        return Optional.ofNullable(user.get());
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        DatabaseOperation op = connection -> {
            final String query = "SELECT * FROM users";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                users.add(new User(id, username, password));
            }
        };
        DatabaseOperationHandler.handleOperation(op);
        return users;
    }
}
