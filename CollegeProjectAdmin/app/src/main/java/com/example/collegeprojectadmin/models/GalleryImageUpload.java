package com.example.collegeprojectadmin.models;

public class GalleryImageUpload {

    private String galleryImageUri;

    public GalleryImageUpload() {
    }

    public GalleryImageUpload(String galleryImageUri) {
        this.galleryImageUri = galleryImageUri;
    }

    public String getGalleryImageUri() {
        return galleryImageUri;
    }

    public void setGalleryImageUri(String galleryImageUri) {
        this.galleryImageUri = galleryImageUri;
    }
}
