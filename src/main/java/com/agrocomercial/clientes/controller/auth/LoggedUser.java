package com.agrocomercial.clientes.controller.auth;

import com.agrocomercial.clientes.utils.Roles;

import java.util.HashSet;
import java.util.Set;

public class LoggedUser {
    private Integer userId;
    private String username;
    private Integer customerId;
    private Integer adminId;
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

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
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
}
