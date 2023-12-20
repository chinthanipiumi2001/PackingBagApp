package com.example.packingbagapp.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.packingbagapp.Models.Items;

@Database(entities = Items.class,version = 1,exportSchema = false)
public abstract class RoomDB extends RoomDatabase {

    private static RoomDB database;
    private static String DATABASE_NAME ="MyDb";

    public synchronized static RoomDB getInstance(Context context){
        if (database == null){

        }
        return database;
    }
}
