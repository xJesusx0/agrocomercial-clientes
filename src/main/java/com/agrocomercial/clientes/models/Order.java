package com.agrocomercial.clientes.models;

public class Order extends BaseEntity {

    private Long orderNumber;

    // FK
    private Integer idCustomer;

    public Order(Long orderNumber, Integer idCustomer) {
        this.orderNumber = orderNumber;
        this.idCustomer = idCustomer;
    }

    public Order(Integer id, Long orderNumber, Integer idCustomer) {
        super(id);
        this.orderNumber = orderNumber;
        this.idCustomer = idCustomer;
    }

    public Long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Integer getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Integer idCustomer) {
        this.idCustomer = idCustomer;
    }
}
