package com.example.demofacebook.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GalleryItem implements Serializable {
    @SerializedName("fileId")
    private int galleryItemId;
    @SerializedName("filePath")
    private String imageItemGallery;


    public GalleryItem(int galleryItemId, String imageItemGallery) {
        this.galleryItemId = galleryItemId;
        this.imageItemGallery = imageItemGallery;
    }

    public int getGalleryItemId() {
        return galleryItemId;
    }

    public void setGalleryItemId(int galleryItemId) {
        this.galleryItemId = galleryItemId;
    }

    public String getImageItemGallery() {
        return imageItemGallery;
    }

    public void setImageItemGallery(String imageItemGallery) {
        this.imageItemGallery = imageItemGallery;
    }
}
