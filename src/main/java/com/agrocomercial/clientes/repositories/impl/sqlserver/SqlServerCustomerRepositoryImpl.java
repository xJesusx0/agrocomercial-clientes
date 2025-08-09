package com.agrocomercial.clientes.repositories.impl.sqlserver;

import com.agrocomercial.clientes.database.DatabaseOperation;
import com.agrocomercial.clientes.database.DatabaseOperationHandler;
import com.agrocomercial.clientes.models.Customer;
import com.agrocomercial.clientes.repositories.CustomerRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class SqlServerCustomerRepositoryImpl implements CustomerRepository {
	@Override
	public Optional<Customer> findByUserId(Integer userId) {
		AtomicReference<Customer> customer = new AtomicReference<>();
		DatabaseOperation findByUserId = connection -> {
			final String query = "SELECT * " +
					"FROM compradores c " +
					"WHERE c.id_usuario = ? ";

			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, userId);
			ResultSet resultSet = preparedStatement.executeQuery();

			if(resultSet.next()) {
				Integer id = resultSet.getInt("id_comprador");
				String name = resultSet.getString("nombre_comprador");
				String lastname = resultSet.getString("apellido_comprador");
				String phone = resultSet.getString("telefono");
				String identification = resultSet.getString("numero_documento");
				Integer idDocumentType = resultSet.getInt("id_tipo_documento");
				Integer idUser = resultSet.getInt("id_usuario");

				customer.set(new Customer(
								id,
								name,
								lastname,
								phone,
								identification,
								idDocumentType,
								idUser
						));

			}
		};

		DatabaseOperationHandler.handleOperation(findByUserId);
		return Optional.ofNullable(customer.get());
	}

	@Override
	public List<Customer> findAll() {
		List<Customer> customers = new ArrayList<>();
		DatabaseOperation findAll = connection -> {
			final String query = "SELECT * FROM compradores c";
			PreparedStatement preparedStatement = connection.prepareStatement(query);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Customer customer = new Customer();
				customer.setId(resultSet.getInt("id_comprador"));
				customer.setName(resultSet.getString("nombre_comprador"));
				customer.setLastname(resultSet.getString("apellido_comprador"));
				customer.setPhoneNumber(resultSet.getString("telefono"));
				customer.setIdentification(resultSet.getString("numero_documento"));
				customer.setIdDocumentType(resultSet.getInt("id_tipo_documento"));
				customer.setIdUser(resultSet.getInt("id_usuario"));
				customers.add(customer);
			}
		};

		DatabaseOperationHandler.handleOperation(findAll);

		return customers;
	}

}
