package com.example.lifetrack.utilities.app;

import android.app.Application;


public class App extends Application {
    static App app;


    public static App getApp() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;

    }


}

