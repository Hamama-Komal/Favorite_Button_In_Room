package com.example.favoritebuttoninroom.model;

public class MainModel {

    public int id;
    public String name;
    public String email;
    public String gender;
    public String city;
    public int age;
    public String image;
    public int work;


    public MainModel(int id, String name, String email, String gender, String city, int age, String image, int work) {
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
