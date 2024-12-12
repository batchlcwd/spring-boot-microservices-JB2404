package com.course.management;

import com.course.management.dao.CategoryDao;
import com.course.management.dao.CourseDao;
import com.course.management.entities.Category;
import com.course.management.entities.Course;
import com.course.management.entities.CourseCategoryData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class CourseJdbcExampleApplication implements CommandLineRunner {

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private CourseDao courseDao;

    public static void main(String[] args) {
        SpringApplication.run(CourseJdbcExampleApplication.class, args);
        // categoryDao

    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("application started");

        //category create karne ja rhe hai
//		Category category = new Category();
//		category.setId(102);
//		category.setTitle("Live Batches");
//		category.setDescription("This is live batch category");
//
//		Category savedCategory = categoryDao.save(category);
//		System.out.println("category created with id "+savedCategory.getId());

//        List<Category> categoriesList = categoryDao.getAll();
//
//        categoriesList.forEach(category -> {
//            System.out.println(category.getTitle()+"\t"+category.getId());
//        });

//        Category category = categoryDao.get(102);
//        System.out.println(category.getTitle());

//        categoryDao.delete(102);

//        Course course = new Course();
//        course.setCategoryId(101);
//        course.setCourseId(10004);
//        course.setTitle("String Boot course- Live");
//        course.setDescription("This is first course.");
//        course.setPrice(4000);
//
//        courseDao.save(course);

//        Course course = courseDao.get(10003);
//        System.out.println(course.getTitle());

//        List<Course> courseByCategory = courseDao.getCourseByCategory(101);
//        courseByCategory.forEach(course -> System.out.println(course.getTitle()));

        List<CourseCategoryData> courseCategoryData = courseDao.courseCatData();
        courseCategoryData.forEach(data->{
            System.out.println(data.getCourseTitle()+"\t"+data.getCategoryTitle());
        });


    }
}
