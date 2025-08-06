package com.agrocomercial.clientes.models;

public class Location extends BaseEntity {

  public Location(String name, String address) {
    this.name = name;
    this.address = address;
  }

  public Location(Integer id, String name, String address) {
    super(id);
    this.name = name;
    this.address = address;
  }

  private String name;
  private String address;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }
}
