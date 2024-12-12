package com.course.management.entities;

public class Course {

    private int courseId;
    private String title;
    private String description;
    private int price;
    private int categoryId;




//    .....


    public Course(int courseId, String title, String description, int price) {
        this.courseId = courseId;
        this.title = title;
        this.description = description;
        this.price = price;
    }

    public Course() {
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
