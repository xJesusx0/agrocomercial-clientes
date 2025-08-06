package com.agrocomercial.clientes.models;

public class Customer extends BaseEntity {

  private String name;
  private String lastname;
  private String phoneNumber;

  // FK
  private Integer idDocumentType;
  private Integer idUser;

  public Customer(String name, String lastname, String phoneNumber, Integer idDocumentType, Integer idUser) {
    this.name = name;
    this.lastname = lastname;
    this.phoneNumber = phoneNumber;
    this.idDocumentType = idDocumentType;
    this.idUser = idUser;
  }

  public Customer(Integer id, String name, String lastname, String phoneNumber, Integer idDocumentType,
          Integer idUser) {
    super(id);
    this.name = name;
    this.lastname = lastname;
    this.phoneNumber = phoneNumber;
    this.idDocumentType = idDocumentType;
    this.idUser = idUser;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public Integer getIdDocumentType() {
    return idDocumentType;
  }

  public void setIdDocumentType(Integer idDocumentType) {
    this.idDocumentType = idDocumentType;
  }

  public Integer getIdUser() {
    return idUser;
  }

  public void setIdUser(Integer idUser) {
    this.idUser = idUser;
  }
}
