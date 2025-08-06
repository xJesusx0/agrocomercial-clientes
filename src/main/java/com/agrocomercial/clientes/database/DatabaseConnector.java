package com.agrocomercial.clientes.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {

    private DatabaseConnector(){}

    private static final String URL = "jdbc:mysql://localhost:3306/agrocomercial";
    private static final String USER = "root";
    private static final String PASSWORD = "081880";

    public static Connection connect(){
        Connection connection;

        try{
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            if(connection != null){
                System.out.println("Conexion con la base de datos exitosa");
            }

            return connection;

        } catch (SQLException e) {
            System.out.println("Ha ocurrido un error ");
            e.printStackTrace();
        }

        System.out.println("No se ha podido conectar con la base de datos");
        return null;
    }

}
