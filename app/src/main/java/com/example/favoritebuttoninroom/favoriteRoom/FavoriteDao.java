package com.example.favoritebuttoninroom.favoriteRoom;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FavoriteDao {

    @Insert
    void insertData(FavoriteModel model);

    @Query("SELECT * FROM favorite")
    List<FavoriteModel> getAllData();

    @Query("SELECT EXISTS (SELECT 1 FROM favorite WHERE id = :id)")
    boolean isFavorite(int id);

    @Query("DELETE FROM favorite WHERE id = :id")
    void deleteData(int id);
}
