package com.agrocomercial.clientes.services;

public class UserService {

    public boolean authenticateUser(String username, String password){
        return username.equals("jesus") && password.equals("jesus1234");
    }

}
