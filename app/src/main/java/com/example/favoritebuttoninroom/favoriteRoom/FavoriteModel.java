package com.example.favoritebuttoninroom.favoriteRoom;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "favorite", indices = {@Index(value = {"name"}, unique = true), @Index(value = {"email"}, unique = true), @Index(value = {"gender"}, unique = false), @Index(value = {"city"}, unique = true), @Index(value = {"age"}, unique = false), @Index(value = {"image"}, unique = false), @Index(value = {"work"}, unique = false)})
public class FavoriteModel {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "email")
    public String email;

    @ColumnInfo(name = "gender")
    public String gender;

    @ColumnInfo(name = "city")
    public String city;

    @ColumnInfo(name = "age")
    public int age;

    @ColumnInfo(name = "image")
    public String image;

    @ColumnInfo(name = "work")
    public int work;






    public FavoriteModel(int id, String name, String email, String gender, String city, int age, String image, int work) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.city = city;
        this.age = age;
        this.image = image;
        this.work = work;
    }
}
