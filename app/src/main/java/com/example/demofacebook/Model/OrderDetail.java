package com.example.demofacebook.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class OrderDetail implements Serializable {
    @SerializedName("order")
    private Order order;
    @SerializedName("orderDetailId")
    private int OrderDetailId;
    @SerializedName("price")
    private int price;
    @SerializedName("discount")
    private int discount;
    @SerializedName("rating")
    private int rating;
    @SerializedName("content")
    private String content;
    @SerializedName("postDate")
    private Date postDate;


    @SerializedName("servicePack")
    private Service servicePack;


    public OrderDetail(Order order, int orderDetailId, int price, int discount, int rating, String content, Date postDate, Service servicePack) {
        this.order = order;
        OrderDetailId = orderDetailId;
        this.price = price;
        this.discount = discount;
        this.rating = rating;
        this.content = content;
        this.postDate = postDate;
        this.servicePack = servicePack;
    }

    public OrderDetail(int rating, String content) {
        this.rating = rating;
        this.content = content;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getOrderDetailId() {
        return OrderDetailId;
    }

    public void setOrderDetailId(int orderDetailId) {
        OrderDetailId = orderDetailId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public Service getServicePack() {
        return servicePack;
    }

    public void setServicePack(Service servicePack) {
        this.servicePack = servicePack;
    }

}
