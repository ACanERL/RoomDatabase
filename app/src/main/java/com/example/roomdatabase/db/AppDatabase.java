package com.example.roomdatabase.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities = {Kategori.class},version = 1)
public  abstract  class AppDatabase extends RoomDatabase {
    public static AppDatabase INSTANCE;
    public abstract AppDao appDao();
    public static AppDatabase getDatabaseInstance(Context context){
        if(INSTANCE==null){
            INSTANCE= Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class,"DB")
                    .allowMainThreadQueries().build();
        }
        return INSTANCE;
    }

}
