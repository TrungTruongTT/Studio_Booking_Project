package com.example.demofacebook.Model;

import java.sql.Date;

public class Notification {
    private int notificationId;
    private int image;
    private String notificationTitle;
    private String notification;
    private Date date;


    public Notification(int notificationId, int image, String notificationTitle, String notification, Date date) {
        this.notificationId = notificationId;
        this.image = image;
        this.notificationTitle = notificationTitle;
        this.notification = notification;
        this.date = date;
    }

    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getNotificationTitle() {
        return notificationTitle;
    }

    public void setNotificationTitle(String notificationTitle) {
        this.notificationTitle = notificationTitle;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
