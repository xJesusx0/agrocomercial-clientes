package com.agrocomercial.clientes.models;

public class BaseEntity {
  protected Integer id;

  public BaseEntity() {}

  public BaseEntity(Integer id) {
    this.id = id;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }
}
