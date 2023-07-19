package com.example.demofacebook.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class OrderInformation implements Serializable {
    @SerializedName("orderId")
    private int orderId;
    @SerializedName("orderDate")
    private String orderDate;
    @SerializedName("status")
    private String status;
    @SerializedName("description")
    private String description;
    @SerializedName("checkIn")
    private String checkIn;


    public OrderInformation(int orderId, String orderDate, String status, String description, String checkIn) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.status = status;
        this.description = description;
        this.checkIn = checkIn;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }
}
