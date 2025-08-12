package com.agrocomercial.clientes.repositories.impl.sqlserver;

import com.agrocomercial.clientes.database.DatabaseOperation;
import com.agrocomercial.clientes.database.DatabaseOperationHandler;
import com.agrocomercial.clientes.models.Product;
import com.agrocomercial.clientes.repositories.ProductRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SqlServerProductRepositoryImpl implements ProductRepository {
	@Override
	public List<Product> findAll() {
		List<Product> products = new ArrayList<Product>();
		DatabaseOperation findAll = connection -> {
			final String query = "SELECT * FROM productos";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Product product = new Product();
				product.setId(resultSet.getInt("id_producto"));
				product.setName(resultSet.getString("nombre_producto"));
				product.setPrice(resultSet.getDouble("precio"));
				product.setDescription(resultSet.getString("descripcion_producto"));

				products.add(product);
			}
		};

		DatabaseOperationHandler.handleOperation(findAll);
		return products;
	}

	@Override
	public Product save(Product product) {
		DatabaseOperation saveOp = connection -> {
			final String query = "INSERT INTO productos (nombre_producto, descripcion_producto, precio) VALUES (?, ?, ?);";
			PreparedStatement ps = connection.prepareStatement(query, java.sql.Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, product.getName());
			ps.setString(2, product.getDescription());
			ps.setDouble(3, product.getPrice());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				product.setId(rs.getInt(1));
			}
		};
		DatabaseOperationHandler.handleOperation(saveOp);
		return product;
	}
}
