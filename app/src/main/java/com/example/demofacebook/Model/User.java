package com.example.demofacebook.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User implements Serializable {
    @SerializedName("accountId")
    private int userId;
    @SerializedName("avatar")
    private String image;
    @SerializedName("fullName")
    private String fullName;
    @SerializedName("phone")
    private String phone;
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;


    public User(String fullName, String email, String phone, String password, String image) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.image = image;
    }

    public User(int userId, String image, String fullName, String phone, String email, String password) {
        this.userId = userId;
        this.image = image;
        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }
    //for return Create

    public User(String image, String fullName, String password) {
        this.image = image;
        this.fullName = fullName;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", image='" + image + '\'' +
                ", fullName='" + fullName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }



    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getImage() {
            return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
