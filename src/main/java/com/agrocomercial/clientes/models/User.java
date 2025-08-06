package com.agrocomercial.clientes.models;

public class User extends BaseEntity {
  public User(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public User(Integer id, String username, String password) {
    super(id);
    this.username = username;
    this.password = password;
  }

  private String username;
  private String password;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
