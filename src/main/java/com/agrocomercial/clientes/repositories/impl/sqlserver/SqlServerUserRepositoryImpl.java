package com.agrocomercial.clientes.repositories.impl.sqlserver;

import com.agrocomercial.clientes.database.DatabaseOperation;
import com.agrocomercial.clientes.database.DatabaseOperationHandler;
import com.agrocomercial.clientes.models.User;
import com.agrocomercial.clientes.repositories.UserRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class SqlServerUserRepositoryImpl implements UserRepository {
	@Override
	public Optional<User> findByUsernameAndPassword(String username, String password) {
		AtomicReference<User> user = new AtomicReference<>();
		DatabaseOperation findUserByUsernameAndPassword = connection -> {
			String query = "SELECT * FROM usuarios WHERE nombre_usuario = ? AND contraseña = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1,username);
			preparedStatement.setString(2,password);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				Integer storedId = resultSet.getInt("id_usuario");
				String storedUsername = resultSet.getString("nombre_usuario");
				String storedPassword = resultSet.getString("contraseña");

				user.set(new User(storedId, storedUsername, storedPassword));
			}
		};

		DatabaseOperationHandler.handleOperation(findUserByUsernameAndPassword);

		return Optional.ofNullable(user.get());
	}
}
