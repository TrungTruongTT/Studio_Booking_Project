package com.example.demofacebook.Model;

import java.io.Serializable;
import java.sql.Date;

public class User implements Serializable {
    private int userId;
    private String image;
    private String Name;

    private String userName;

    private Date dateOfBirth;
    private String phone;
    private String email;
    private String password;

    //registerModel
    public User (String userName, String phone, String Email, String password)
    {

    }

    public User(int userId, String image, String name, Date dateOfBirth, String phone, String email, String password) {
        this.userId = userId;
        this.image = image;
        Name = name;
        this.dateOfBirth = dateOfBirth;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
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
}
