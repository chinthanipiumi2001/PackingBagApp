package com.example.packingbagapp.Data;

import android.app.Application;

import com.example.packingbagapp.Database.RoomDB;

public class AppData extends Application {

    RoomDB database;
    String category;

    public static final String LAST_VERSION = "LAST_VERSION";
    public static final int NEW_VERSION = 3;

    public AppData (RoomDB database){
        this.database = database;
    }

}
