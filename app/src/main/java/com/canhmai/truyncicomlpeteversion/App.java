package com.canhmai.truyncicomlpeteversion;

import android.app.Application;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.room.Room;

import com.canhmai.truyncicomlpeteversion.db.AppDB;

public class App extends Application {
    private static App instance;
    private Storage storage;
    private AppDB appDB;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        storage = new Storage();
        appDB = Room.databaseBuilder(this, AppDB.class, "truyencuoi2016.db").createFromAsset("db/truyencuoi2016.db").allowMainThreadQueries().build();
        instance = this;
        checkNighMode();
    }

    private void checkNighMode() {
        boolean value = MySharePreference.getInstance().getBooleanValue(MySharePreference.NIGH_MODE);

        if (value) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            App.getInstance().getStorage().isModeNigh = true;
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            App.getInstance().getStorage().isModeNigh = false;
        }

    }

    public AppDB getAppDB() {
        return appDB;
    }

    public Storage getStorage() {
        return storage;
    }
}
