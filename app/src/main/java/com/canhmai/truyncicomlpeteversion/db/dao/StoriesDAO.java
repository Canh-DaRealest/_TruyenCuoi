package com.canhmai.truyncicomlpeteversion.db.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Update;

import com.canhmai.truyncicomlpeteversion.db.entity.Story;

import java.util.List;

@Dao
public interface StoriesDAO {
    @Query("SELECT * FROM Story WHERE cat_name = :catName")
    List<Story> getStoryByCatName(String catName);

    @Query("SELECT * FROM Story WHERE is_favourite = 1")
    List<Story> getFavouriteStory();

    @Query("SELECT * FROM Story")
    List<Story> getAll();

    @Query("SELECT * FROM Story ORDER BY random() LIMIT 20")
    List<Story> get20Story();

    @Query("SELECT * FROM Story WHERE name like :a || '%'")
    List<Story> getStoryByChar(String a);

    @Update
    void updateStory(Story story);

}
