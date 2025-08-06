package com.agrocomercial.clientes.models;

public class Administrator extends BaseEntity {

  private String name;
  private String lastname;
  private String phoneNumber;

  // FK
  private Integer idDocumentType;
  private Integer idLocation;
  private Integer idUser;

  public Administrator(String name, String lastname, String phoneNumber, String identification, Integer idDocumentType,
          Integer idLocation, Integer idUser) {
    this.name = name;
    this.lastname = lastname;
    this.phoneNumber = phoneNumber;
    this.identification = identification;
    this.idDocumentType = idDocumentType;
    this.idLocation = idLocation;
    this.idUser = idUser;
  }

  public Administrator(Integer id, String name, String lastname, String phoneNumber, String identification,
          Integer idDocumentType, Integer idLocation, Integer idUser) {
    super(id);
    this.name = name;
    this.lastname = lastname;
    this.phoneNumber = phoneNumber;
    this.identification = identification;
    this.idDocumentType = idDocumentType;
    this.idLocation = idLocation;
    this.idUser = idUser;
  }

  private String identification;



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

  public String getIdentification() {
    return identification;
  }

  public void setIdentification(String identification) {
    this.identification = identification;
  }

  public Integer getIdDocumentType() {
    return idDocumentType;
  }

  public void setIdDocumentType(Integer idDocumentType) {
    this.idDocumentType = idDocumentType;
  }

  public Integer getIdLocation() {
    return idLocation;
  }

  public void setIdLocation(Integer idLocation) {
    this.idLocation = idLocation;
  }

  public Integer getIdUser() {
    return idUser;
  }

  public void setIdUser(Integer idUser) {
    this.idUser = idUser;
  }
}
