package com.course.management.service;

import com.course.management.dao.CategoryDao;
import com.course.management.dao.CourseDao;
import com.course.management.entities.Category;
import com.course.management.entities.Course;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service

public class CategoryService {

    private CourseDao courseDao;
    private CategoryDao categoryDao;

    public CategoryService(CourseDao courseDao, CategoryDao categoryDao) {
        this.courseDao = courseDao;
        this.categoryDao = categoryDao;
    }

    @Transactional
    public void saveCategoryWithCourse(Category category, Course course){
        categoryDao.save(category);
        courseDao.save(course);
    }
}
