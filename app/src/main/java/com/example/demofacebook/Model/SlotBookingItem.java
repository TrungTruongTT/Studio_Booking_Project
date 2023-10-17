package com.example.demofacebook.Model;

public class SlotBookingItem {
    private int id;
    private String time;
    private Integer rating;
    private String content;
    private String postDate;

    public SlotBookingItem(int id, String time, Integer rating, String content, String postDate) {
        this.id = id;
        this.time = time;
        this.rating = rating;
        this.content = content;
        this.postDate = postDate;
    }
    public SlotBookingItem(int id, String time) {
        this.id = id;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "SlotBookingItem{" +
                "id=" + id +
                ", time='" + time + '\'' +
                ", rating=" + rating +
                ", content='" + content + '\'' +
                '}';
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }
}
