package com.example.demofacebook.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class OrderInformation implements Serializable {
    @SerializedName("orderId")
    private int orderId;
    @SerializedName("orderDate")
    private String orderDate;
    @SerializedName("status")
    private String status;
    @SerializedName("paymentDate")
    private String paymentDate;
    @SerializedName("totalPrice")
    private String totalPrice;
    @SerializedName("studioId")
    private int studioId;
    @SerializedName("orderDetails")
    private List<OrderDetail> orderDetail;


    public OrderInformation(int orderId, String orderDate, String status, String paymentDate, String totalPrice, int studioId, List<OrderDetail> orderDetail) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.status = status;
        this.paymentDate = paymentDate;
        this.totalPrice = totalPrice;
        this.studioId = studioId;
        this.orderDetail = orderDetail;
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

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getStudioId() {
        return studioId;
    }

    public void setStudioId(int studioId) {
        this.studioId = studioId;
    }

    public List<OrderDetail> getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(List<OrderDetail> orderDetail) {
        this.orderDetail = orderDetail;
    }
}
