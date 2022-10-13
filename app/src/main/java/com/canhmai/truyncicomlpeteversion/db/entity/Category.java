package com.canhmai.truyncicomlpeteversion.db.entity;

import android.graphics.Bitmap;

public class Category {

    private String name;
    private Bitmap image;

    public Category(String name, Bitmap image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
