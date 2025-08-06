package com.agrocomercial.clientes.database;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Interfaz comun para todas las consultas a la base de datos
 *
 * @author Jesús Perea
 * @from <a href=
 *       "https://github.com/xJesusx0/restaurant-manager/blob/main/RestaurantManager/src/main/java/co/jesus/RestaurantManager/database/DatabaseOperation.java">DatabaseOperations</a>
 */
public interface DatabaseOperation {
  void execute(Connection connection) throws SQLException;
}
