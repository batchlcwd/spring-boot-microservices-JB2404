package com.course.management.dao;

import com.course.management.entities.Category;
import com.course.management.entities.Course;
import com.course.management.entities.CourseCategoryData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class CourseDao {


    @Autowired
    private JdbcTemplate jdbcTemplate;


    //insert course in batch

    @Transactional
    public void saveCategoryThanCourse(Category category,Course course){

//        inserting category: code
//            create category query
//        jdbc fire: update

//        insert karte course co
//        create course query
//        jdbc template ki help se fire
    }



    public void saveCourseInBatch(List<Course> list) {


        //single course
        String query = "INSERT INTO courses (courseId, title, description, price, category_id)\n" +
                "VALUES (?, ?, ?, ?, ?)";

        int[][] ints = jdbcTemplate.batchUpdate(query, list, list.size(), (ps, course) -> {
            ps.setInt(1, course.getCourseId());
            ps.setString(2, course.getTitle());
            ps.setString(3, course.getDescription());
            ps.setInt(4, course.getPrice());
            ps.setInt(5, course.getCategoryId());
        });


    }

    //create course
    public Course save(Course course) {


        String query = "INSERT INTO courses (courseId, title, description, price, category_id)\n" +
                "VALUES (?, ?, ?, ?, ?)";
        int row = jdbcTemplate.update(query,
                course.getCourseId(),
                course.getTitle(),
                course.getDescription(),
                course.getPrice(),
                course.getCategoryId()
        );

        System.out.println(row + " rows effected");

        //query
        //jdbc template ki help se fire
        return course;


    }

    //get all course

    public List<Course> getAll() {
        String query = "select * from courses";
        List<Course> courses = jdbcTemplate.query(query, (rs, num) -> {
            Course course = new Course();
            course.setCourseId(rs.getInt("courseId"));
            course.setTitle(rs.getString("title"));
            course.setDescription(rs.getString("description"));
            course.setCategoryId(rs.getInt("category_id"));
            return course;
        });

        return courses;
    }

    // get single course

    public Course get(int courseId) {
        String query = "select * from courses where courseId = ?";
        Course course1 = jdbcTemplate.queryForObject(query, (rs, num) -> {
            Course course = new Course();
            course.setCourseId(rs.getInt("courseId"));
            course.setTitle(rs.getString("title"));
            course.setDescription(rs.getString("description"));
            course.setCategoryId(rs.getInt("category_id"));
            return course;
        }, courseId);
        return course1;
    }

    // update course
    // do by yourself

    //delete course
    //do by yourself
    public List<Course> getCourseByCategory(int categoryId) {
        String query = "select * from courses where category_id=?";
        List<Course> courseList = jdbcTemplate.query(query, (rs, num) -> {
            Course course = new Course();
            course.setCourseId(rs.getInt("courseId"));
            course.setTitle(rs.getString("title"));
            course.setDescription(rs.getString("description"));
            course.setCategoryId(rs.getInt("category_id"));
            return course;
        }, categoryId);

        return courseList;
    }

    public List<CourseCategoryData> courseCatData() {
        String query = "SELECT \n" +
                "    categories.title AS categoryTitle,\n" +
                "    categories.description AS categoryDesc,\n" +
                "    courses.title AS courseTitle,\n" +
                "    courses.price AS coursePrice,\n" +
                "    courses.description AS courseDesc\n" +
                "FROM \n" +
                "    categories\n" +
                "INNER JOIN \n" +
                "    courses\n" +
                "ON \n" +
                "    categories.id = courses.category_id;\n";

        List<CourseCategoryData> courseCategoryData1 = jdbcTemplate.query(query, (rs, rowNum) -> {
            CourseCategoryData courseCategoryData = new CourseCategoryData();
            courseCategoryData.setCategoryTitle(rs.getString("categoryTitle"));
            courseCategoryData.setCategoryDesc(rs.getString("categoryDesc"));
            courseCategoryData.setCourseTitle(rs.getString("courseTitle"));
            courseCategoryData.setCourseDesc(rs.getString("courseDesc"));
            courseCategoryData.setCoursePrice(rs.getInt("coursePrice"));
            return courseCategoryData;
        });

        return courseCategoryData1;

    }

}
