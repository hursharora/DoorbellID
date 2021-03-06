package com.example.dooridentify.model;

public class Profile {

    private String name;
    private String description;
    private String imgURL;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImgURL() {
        return imgURL;
    }

    public Profile (String name, String description, String imgURL) {
        this.imgURL = imgURL;
        this.name = name;
        this.description = description;
    }



}
