package com.course.management.entities;


public class Category {

    private int id;
    private String title;
    private String description;

    /// required properties


    public Category(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public Category() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
