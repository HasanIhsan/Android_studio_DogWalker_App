package com.example.hassanihsan.project1dogwalker;

public class DogModelClass {

    //variables
    Integer id;
    String name;
    String date;

    //consturtor methods
    public DogModelClass(String name, String date) {
        this.name = name;
        this.date = date;
    }

    public DogModelClass(Integer id, String name, String date) {
        this.id = id;
        this.name = name;
        this.date = date;
    }

    //get & set methods
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
