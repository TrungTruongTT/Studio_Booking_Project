package com.example.demofacebook.Model;

import java.util.Date;

public class Order {

    private int orderId;
    private Date orderDate;
    private int status;
    private int totalPrice;
    private int totalOrderDetail;
    private String urlImageService;
    private String serviceName;

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderDate=" + orderDate +
                ", status=" + status +
                ", totalPrice=" + totalPrice +
                ", totalOrderDetail=" + totalOrderDetail +
                ", urlImageService='" + urlImageService + '\'' +
                ", serviceName='" + serviceName + '\'' +
                '}';
    }

    public Order(int orderId, Date orderDate, int status, int totalPrice,
                 int totalOrderDetail, String urlImageService, String serviceName) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.status = status;
        this.totalPrice = totalPrice;
        this.totalOrderDetail = totalOrderDetail;
        this.urlImageService = urlImageService;
        this.serviceName = serviceName;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getTotalOrderDetail() {
        return totalOrderDetail;
    }

    public void setTotalOrderDetail(int totalOrderDetail) {
        this.totalOrderDetail = totalOrderDetail;
    }

    public String getUrlImageService() {
        return urlImageService;
    }

    public void setUrlImageService(String urlImageService) {
        this.urlImageService = urlImageService;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}
