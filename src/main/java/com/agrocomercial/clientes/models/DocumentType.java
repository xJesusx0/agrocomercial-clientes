package com.agrocomercial.clientes.models;

public class DocumentType extends BaseEntity {

  private final String code;
  private final String name;

  public DocumentType(String code, String name) {
    this.code = code;
    this.name = name;
  }

  public DocumentType(Integer id, String code, String name) {
    super(id);
    this.code = code;
    this.name = name;
  }

  public String getCode() {
    return code;
  }

  public String getName() {
    return name;
  }
}
