/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author n0252282
 */
public class Order {

    private Integer orderNumber;
    private LocalDate orderDate;
    private String customerName;
    private Tax taxRate;
    private Product product;
    private BigDecimal area;
    
    //calulated fields
    private BigDecimal materialCost;
    private BigDecimal laborCost;
    private BigDecimal taxTotal;
    private BigDecimal totalCost;

    public Order(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Tax getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Tax taxRate) {
        this.taxRate = taxRate;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public BigDecimal getMaterialCost() {
        return product.getCostPerSquareFoot().multiply(this.area);
    }

    public BigDecimal getLaborCost() {
        return product.getLaborCostPerSquareFoot().multiply(this.area);
    }

    public BigDecimal getTaxTotal() {
        return taxRate.getTaxRate().multiply(this.getMaterialCost()).multiply(this.getLaborCost());
    }

    public BigDecimal getTotalCost() {
        return this.getLaborCost().add(this.getTaxTotal()).add(this.getMaterialCost());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.orderNumber);
        hash = 59 * hash + Objects.hashCode(this.orderDate);
        hash = 59 * hash + Objects.hashCode(this.customerName);
        hash = 59 * hash + Objects.hashCode(this.taxRate);
        hash = 59 * hash + Objects.hashCode(this.product);
        hash = 59 * hash + Objects.hashCode(this.area);
        hash = 59 * hash + Objects.hashCode(this.materialCost);
        hash = 59 * hash + Objects.hashCode(this.laborCost);
        hash = 59 * hash + Objects.hashCode(this.taxTotal);
        hash = 59 * hash + Objects.hashCode(this.totalCost);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Order other = (Order) obj;
        if (!Objects.equals(this.customerName, other.customerName)) {
            return false;
        }
        if (!Objects.equals(this.orderNumber, other.orderNumber)) {
            return false;
        }
        if (!Objects.equals(this.orderDate, other.orderDate)) {
            return false;
        }
        if (!Objects.equals(this.taxRate, other.taxRate)) {
            return false;
        }
        if (!Objects.equals(this.product, other.product)) {
            return false;
        }
        if (!Objects.equals(this.area, other.area)) {
            return false;
        }
        if (!Objects.equals(this.materialCost, other.materialCost)) {
            return false;
        }
        if (!Objects.equals(this.laborCost, other.laborCost)) {
            return false;
        }
        if (!Objects.equals(this.taxTotal, other.taxTotal)) {
            return false;
        }
        if (!Objects.equals(this.totalCost, other.totalCost)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Order{" + "orderNumber=" + orderNumber + ", orderDate=" + orderDate + ", customerName=" + customerName + ", taxRate=" + taxRate + ", product=" + product + ", area=" + area + ", materialCost=" + materialCost + ", laborCost=" + laborCost + ", taxTotal=" + taxTotal + ", totalCost=" + totalCost + '}';
    }

}
