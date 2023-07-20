package com.example.demofacebook.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {
    @SerializedName("orderId")
    private int orderId;
    @SerializedName("orderDate")
    private Date orderDate;
    @SerializedName("status")
    private String status;
    @SerializedName("deposit")
    private int deposit;
    @SerializedName("checkIn")
    private Date checkIn;
    @SerializedName("description")
    private String description;

    public Order(int orderId, Date orderDate, String status, int deposit, Date checkIn, String description) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.status = status;
        this.deposit = deposit;
        this.checkIn = checkIn;
        this.description = description;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getDeposit() {
        return deposit;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
