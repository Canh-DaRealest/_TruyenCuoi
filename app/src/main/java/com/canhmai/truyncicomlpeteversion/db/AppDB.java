package com.canhmai.truyncicomlpeteversion.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.canhmai.truyncicomlpeteversion.db.dao.StoriesDAO;
import com.canhmai.truyncicomlpeteversion.db.entity.Story;

@Database(entities = {Story.class}, version = 2)
public abstract class AppDB extends RoomDatabase {
    public abstract StoriesDAO getStoriesDAO();
}
