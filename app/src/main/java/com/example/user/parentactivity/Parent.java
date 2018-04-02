package com.example.user.parentactivity;

/**
 * Created by User on 3/29/2018.
 */

public class Parent {
    String id;
    String  name, imageUrl;
    String image_path="http://ae.priceomania.com/mobileAppImage/categoryIcon/";

    public Parent(String id, String name, String imageUrl) {
        this.id = id;
        this.name = name;
        this.imageUrl =image_path+imageUrl;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}