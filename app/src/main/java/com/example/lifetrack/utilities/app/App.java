package com.example.lifetrack.utilities.app;

import android.app.Application;

import androidx.room.Room;

import com.example.lifetrack.data.AppDataBase;

public class App extends Application {
    static App app;
    AppDataBase db;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        db = Room.databaseBuilder(getApplicationContext(), AppDataBase.class, "noteDataBase").allowMainThreadQueries().build();
    }

    public static App getApp() {
        return app;
    }

    public AppDataBase getDb() {
        return db;
    }
}
