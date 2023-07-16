package com.example.demofacebook.Model;

public class TalkjsModel {
    private int id;
    private String name;
    private String email;
    private String photoURL;
    private String welcomeMessage;

    public TalkjsModel(int id, String name, String email, String photoURL, String welcomeMessage) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.photoURL = photoURL;
        this.welcomeMessage = welcomeMessage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    public void setWelcomeMessage(String welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
    }
}
