package com.example.demofacebook.Model;

import java.io.Serializable;

public class Login_Request implements Serializable  {

    private String credential;
    private String password;

    public Login_Request(String credential, String password) {
        this.credential = credential;
        this.password = password;
    }

    public String getCredential() {
        return credential;
    }

    public void setCredential(String credential) {
        this.credential = credential;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
