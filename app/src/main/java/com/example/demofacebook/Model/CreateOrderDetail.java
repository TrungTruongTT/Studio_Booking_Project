package com.example.demofacebook.Model;

import com.google.gson.annotations.SerializedName;

public class CreateOrderDetail {
    @SerializedName("discount")
    private int discount;
    @SerializedName("slotBookingId")
    private int slotBookingId;

    public CreateOrderDetail(int discount, int slotBookingId) {
        this.discount = discount;
        this.slotBookingId = slotBookingId;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getSlotBookingId() {
        return slotBookingId;
    }

    public void setSlotBookingId(int slotBookingId) {
        this.slotBookingId = slotBookingId;
    }

    @Override
    public String toString() {
        return "CreateOrderDetail{" +
                "discount=" + discount +
                ", slotBookingId=" + slotBookingId +
                '}';
    }
}
