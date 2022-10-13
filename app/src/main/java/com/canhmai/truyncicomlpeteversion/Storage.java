package com.canhmai.truyncicomlpeteversion;

import com.canhmai.truyncicomlpeteversion.db.entity.Category;
import com.canhmai.truyncicomlpeteversion.db.entity.Story;

import java.util.List;

public class Storage {
    public List<Story> listStory;
    public List<Story> listFavouriteStory;
    public List<Story> randomStoryList;
    public List<String> charList;
    public List<Category> categoryList;

    public String background_color;
    public String text_color;
    public String text_size;
    public boolean isModeNigh;


    private Story choosenStory;

    private String categoryName;

    public Story getChoosenStory() {
        return choosenStory;
    }

    public void setChoosenStory(Story choosenStory) {
        this.choosenStory = choosenStory;
    }


    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setTopicName(String topicName) {
        this.categoryName = topicName;
    }

    @Override
    public String toString() {
        return "Storage{" +
                "listStory=" + listStory + '\'' +
                '}';
    }
}
