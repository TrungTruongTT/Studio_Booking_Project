package com.example.demofacebook.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;


public class CustomerAccount  implements Serializable {

    @SerializedName("customerId")
    private int customerId;
    @SerializedName("birthDate")
    private Date birthDate;
    @SerializedName("createDate")
    private Timestamp createDate;
    @SerializedName("status")
    private int status;
    @SerializedName("address")
    private String address;
    @SerializedName("accountModel")
    private User user;
    //send to Create

    public CustomerAccount(User user) {
        this.user = user;
    }

    public CustomerAccount(int customerId, Date birthDate, Timestamp createDate, int status, String address) {
        this.customerId = customerId;
        this.birthDate = birthDate;
        this.createDate = createDate;
        this.status = status;
        this.address = address;
    }

    //get when Create
    public CustomerAccount(int customerId, Date birthDate, Timestamp createDate, int status, String address, User user) {
        this.customerId = customerId;
        this.birthDate = birthDate;
        this.createDate = createDate;
        this.status = status;
        this.address = address;
        this.user = user;
    }


    public CustomerAccount(int customerId, String address, User user) {
        this.customerId = customerId;
        this.address = address;
        this.user = user;
    }


    @Override
    public String toString() {
        return "CustomerAccount{" +
                "customerId=" + customerId +
                ", birthDate=" + birthDate +
                ", createDate=" + createDate +
                ", status=" + status +
                ", address='" + address + '\'' +
                ", user=" + user +
                '}';
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
