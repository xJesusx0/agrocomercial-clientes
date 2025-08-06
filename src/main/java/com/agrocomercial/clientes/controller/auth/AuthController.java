package com.agrocomercial.clientes.controller.auth;

import com.agrocomercial.clientes.models.Customer;
import com.agrocomercial.clientes.models.Administrator;
import com.agrocomercial.clientes.models.User;
import com.agrocomercial.clientes.services.CustomerService;
import com.agrocomercial.clientes.services.AdministratorService;
import com.agrocomercial.clientes.services.UserService;
import com.agrocomercial.clientes.utils.Roles;

public class AuthController {

    private final UserService userService;
    private final CustomerService customerService;
    private final AdministratorService administratorService;
    private final LoggedUser loggedUser;

    public AuthController(UserService userService, CustomerService customerService, AdministratorService administratorService, LoggedUser loggedUser) {
        this.userService = userService;
        this.customerService = customerService;
        this.administratorService = administratorService;
        this.loggedUser = loggedUser;
    }

    public LoggedUser authenticateUser(String username, String password){
        User user = userService.authenticateUser(username, password);
        if(user == null) {
            return null;
        }

        loggedUser.setUserId(user.getId());
        loggedUser.setUsername(user.getUsername());

        setRoles(loggedUser);
        return loggedUser;
    }

    private void setRoles(LoggedUser loggedUser){
        Customer customer = customerService.findByUserId(loggedUser.getUserId());
        if(customer != null) {
            loggedUser.addRole(Roles.CUSTOMER);
            loggedUser.setCustomerId(customer.getId());
        }
        
        Administrator administrator = administratorService.findByUserId(loggedUser.getUserId());
        if(administrator != null) {
            loggedUser.addRole(Roles.ADMINISTRATOR);
            loggedUser.setAdministratorId(administrator.getId());
        }
    }

}
