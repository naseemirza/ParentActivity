package com.example.user.parentactivity;

/**
 * Created by User on 3/30/2018.
 */

public class Child {
    String id;
    String  name, imageUrl;
    String image_path="http://ae.priceomania.com/mobileappwebservices/getchildcategory?catId=14";

    public Child(String id, String name, String imageUrl) {
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
