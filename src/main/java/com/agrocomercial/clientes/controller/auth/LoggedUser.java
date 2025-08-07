package com.agrocomercial.clientes.controller.auth;

import com.agrocomercial.clientes.utils.Roles;

import java.util.HashSet;
import java.util.Set;

public class LoggedUser {
    private Integer userId;
    private String username;
    private Integer customerId;
    private Integer administratorId;
    private final Set<Roles> roles = new HashSet<>();

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getAdministratorId() {
        return administratorId;
    }

    public void setAdministratorId(Integer administratorId) {
        this.administratorId = administratorId;
    }

    public Set<Roles> getRoles() {
        return roles;
    }

    void addRole(Roles role) {
        if(role == null || roles.contains(role)) {
            return;
        }

        roles.add(role);
    }

    public void clear() {
        this.userId = null;
        this.username = null;
        this.customerId = null;
        this.administratorId = null;
        this.roles.clear();
    }
}
