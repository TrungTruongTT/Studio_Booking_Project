package com.example.demofacebook.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.sql.Date;

public class User implements Serializable {
    @SerializedName("accountId")
    private int userId;
    @SerializedName("avatar")
    private String image;
    @SerializedName("fullName")
    private String fullName;
    @SerializedName("username")
    private String username;


    private Date dateOfBirth;
    @SerializedName("phone")
    private String phone;
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;
    @SerializedName("role")
    private String role;


    @SerializedName("employee")
    private String employee;
    @SerializedName("customer")
    private String customer;
    @SerializedName("administrator")
    private String administrator;



    public User(String image, String fullName, String username, String phone, String email, String password) {
        this.image = image;
        this.fullName = fullName;
        this.username = username;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    public User(int userId, String image, String fullName, String username, String phone, String email, String password, String role) {
        this.userId = userId;
        this.image = image;
        this.fullName = fullName;
        this.username = username;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.role = role;
    }
    //for return Create
    public User(int userId, String image, String fullName, String username, String phone, String email, String password, String role, String employee, String customer, String administrator) {
        this.userId = userId;
        this.image = image;
        this.fullName = fullName;
        this.username = username;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.role = role;
        this.employee = employee;
        this.customer = customer;
        this.administrator = administrator;
    }

    public User(int userId, String image, String fullName, Date dateOfBirth, String phone, String email, String password) {
        this.userId = userId;
        this.image = image;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

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
                ", username='" + username + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", employee='" + employee + '\'' +
                ", customer='" + customer + '\'' +
                ", administrator='" + administrator + '\'' +
                '}';
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getAdministrator() {
        return administrator;
    }

    public void setAdministrator(String administrator) {
        this.administrator = administrator;
    }
}
