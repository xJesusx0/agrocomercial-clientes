package com.agrocomercial.clientes.models;

public class Order extends BaseEntity {

    private Long orderNumber;
    private Double subtotal;
    private Integer totalQuantity;

    // FK
    private Integer idCustomer;

    public Order(Long orderNumber, Integer idCustomer, Double subtotal) {
        this.orderNumber = orderNumber;
        this.idCustomer = idCustomer;
        this.subtotal = subtotal;
        this.totalQuantity = 0;
    }

    public Order(Integer id, Long orderNumber, Integer idCustomer, Double subtotal) {
        super(id);
        this.orderNumber = orderNumber;
        this.idCustomer = idCustomer;
        this.subtotal = subtotal;
        this.totalQuantity = 0;
    }

    public Order(Integer id, Long orderNumber, Integer idCustomer, Double subtotal, Integer totalQuantity) {
        super(id);
        this.orderNumber = orderNumber;
        this.idCustomer = idCustomer;
        this.subtotal = subtotal;
        this.totalQuantity = totalQuantity;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
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

    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
    }
}
