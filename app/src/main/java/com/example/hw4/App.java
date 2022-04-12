package com.example.hw4;

import android.app.Application;

import androidx.room.Room;

import com.example.hw4.room.AppDataBase;

public class App extends Application {
    AppDataBase db;
    public static App app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        db = Room.databaseBuilder(getApplicationContext(), AppDataBase.class, "database").allowMainThreadQueries().build();
    }

    public AppDataBase getDb() {
        return db;
    }

    public static App getApp() {
        return app;
    }
}
