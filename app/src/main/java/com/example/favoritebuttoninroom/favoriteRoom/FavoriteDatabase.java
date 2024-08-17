package com.example.favoritebuttoninroom.favoriteRoom;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {FavoriteModel.class}, version = 3, exportSchema = false)
public abstract class FavoriteDatabase extends RoomDatabase {

    public abstract FavoriteDao getDao();
    private static FavoriteDatabase INSTANCE;

    public static FavoriteDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (FavoriteDatabase.class){
                INSTANCE = Room.databaseBuilder(context, FavoriteDatabase.class, "FavoriteDatabase").allowMainThreadQueries().build();

            }
        }
        return INSTANCE;
    }
}
