package com.example.demofacebook.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ModelCreateOrder {
    @SerializedName("studioId")
    private int studioId;
    @SerializedName("orderDetails")
    private List<CreateOrderDetail> orderDetails;

    public ModelCreateOrder(int studioId, List<CreateOrderDetail> orderDetails) {
        this.studioId = studioId;
        this.orderDetails = orderDetails;
    }

    public int getStudioId() {
        return studioId;
    }

    public void setStudioId(int studioId) {
        this.studioId = studioId;
    }

    public List<CreateOrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<CreateOrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    @Override
    public String toString() {
        return "ModelCreateOrder{" +
                "studioId=" + studioId +
                ", orderDetails=" + orderDetails +
                '}';
    }
}
