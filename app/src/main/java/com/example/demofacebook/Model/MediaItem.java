package com.example.demofacebook.Model;

import java.util.List;

public class MediaItem {
    public static final int TYPE_IMAGE = 1;
    public static final int TYPE_VIDEO = 2;
    public static final int TYPE_IMAGE_SLIDE = 3;

    private int type;
    private Studio studio;
    private String imageResourceUrl;
    private List<String> imageResourceUrlList;
    private String videoUrl;



    //////////////////
    ///list của Studio
    public MediaItem(int type, Studio studio, String imageResourceUrl, List<String> imageResourceUrlList, String videoUrl) {
        this.type = type;
        this.studio = studio;
        this.imageResourceUrl = imageResourceUrl;
        this.imageResourceUrlList = imageResourceUrlList;
        this.videoUrl = videoUrl;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Studio getStudio() {
        return studio;
    }

    public void setStudio(Studio studio) {
        this.studio = studio;
    }

    public String getImageResourceUrl() {
        return imageResourceUrl;
    }

    public void setImageResourceUrl(String imageResourceUrl) {
        this.imageResourceUrl = imageResourceUrl;
    }

    public List<String> getImageResourceUrlList() {
        return imageResourceUrlList;
    }

    public void setImageResourceUrlList(List<String> imageResourceUrlList) {
        this.imageResourceUrlList = imageResourceUrlList;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }



}
