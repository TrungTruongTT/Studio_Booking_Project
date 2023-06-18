package com.example.demofacebook.Model;

import java.io.Serializable;
import java.sql.Date;

public class GalleryItem implements Serializable {
    private int galleryItemId;
    private int imageItemGallery;


    public GalleryItem(int galleryItemId, int imageItemGallery) {
        this.galleryItemId = galleryItemId;
        this.imageItemGallery = imageItemGallery;
    }

    public int getGalleryItemId() {
        return galleryItemId;
    }

    public void setGalleryItemId(int galleryItemId) {
        this.galleryItemId = galleryItemId;
    }

    public int getImageItemGallery() {
        return imageItemGallery;
    }

    public void setImageItemGallery(int imageItemGallery) {
        this.imageItemGallery = imageItemGallery;
    }
}
