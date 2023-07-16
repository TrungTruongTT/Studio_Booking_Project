package com.example.demofacebook.Model;

import com.google.gson.annotations.SerializedName;

public class UserByPhone {
    @SerializedName("customerId")
    private int customerId;
    @SerializedName("accountModel")
    private User user;

    public User getUser() {
        return user;
    }

    public UserByPhone(int customerId, User user) {
        this.customerId = customerId;
        this.user = user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}
