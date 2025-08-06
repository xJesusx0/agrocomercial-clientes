package com.agrocomercial.clientes.database;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {

  private DatabaseConnector() {}

  public static Connection connect() {

    Dotenv dotenv = Dotenv.load();

    String url = dotenv.get("DB_URL");
    String user = dotenv.get("DB_USER");
    String password = dotenv.get("DB_PASSWORD");

    Connection connection;

    try {
      connection = DriverManager.getConnection(url, user, password);
      return connection;

    } catch (SQLException e) {
      System.out.println("Ha ocurrido un error ");
      e.printStackTrace();
    }

    System.out.println("No se ha podido conectar con la base de datos");
    return null;
  }

}
