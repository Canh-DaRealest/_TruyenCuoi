package com.canhmai.truyncicomlpeteversion.db.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Story {

    @ColumnInfo(name = "name")
    @NonNull
    public String name;

    @ColumnInfo(name = "content")
    public String content;

    @ColumnInfo(name = "cat_name")
    @NonNull
    public String catName;

    @ColumnInfo(name = "is_favourite")
    public int isFavourite;
    @ColumnInfo(name = "id")
    @PrimaryKey
    public int id;

    public Story(@NonNull String name, String content, @NonNull String catName, int isFavourite, int id) {
        this.name = name;
        this.content = content;
        this.catName = catName;
        this.isFavourite = isFavourite;
        this.id = id;
    }

    public Story() {
    }

    public Story(String storyName, String content, String catName, int isFavour) {
        this.name = storyName;
        this.content = content;
        this.catName = catName;
        this.isFavourite = isFavour;
    }

    @Override
    public String toString() {
        return "Story{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", catName='" + catName + '\'' +
                ", isFavourite=" + isFavourite +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @NonNull
    public String getCatName() {
        return catName;
    }

    public void setCatName(@NonNull String catName) {
        this.catName = catName;
    }

    public int getIsFavourite() {
        return isFavourite;
    }

    public void setIsFavourite(int isFavourite) {
        this.isFavourite = isFavourite;
    }
}
