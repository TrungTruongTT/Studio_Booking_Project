package com.example.demofacebook.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class OrderDetail implements Serializable {
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
    @SerializedName("slotBookingId")
    private int slotBookingId;
    @SerializedName("postDate")
    private String postDate;
    @SerializedName("startTime")
    private String startTime;
    @SerializedName("endTime")
    private String endTime;

    public OrderDetail(int orderDetailId, int price, int discount, int rating, String content, int slotBookingId, String postDate, String startTime, String endTime) {
        OrderDetailId = orderDetailId;
        this.price = price;
        this.discount = discount;
        this.rating = rating;
        this.content = content;
        this.slotBookingId = slotBookingId;
        this.postDate = postDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public OrderDetail(int rating, String content) {
        this.rating = rating;
        this.content = content;
    }

    public OrderDetail(int slotBookingId, int discount) {
        this.slotBookingId = slotBookingId;
        this.discount = discount;
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

    public int getSlotBookingId() {
        return slotBookingId;
    }

    public void setSlotBookingId(int slotBookingId) {
        this.slotBookingId = slotBookingId;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }


    @Override
    public String toString() {
        return "OrderDetail{" +
                "OrderDetailId=" + OrderDetailId +
                ", price=" + price +
                ", discount=" + discount +
                ", rating=" + rating +
                ", content='" + content + '\'' +
                ", slotBookingId=" + slotBookingId +
                ", postDate='" + postDate + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }
}
