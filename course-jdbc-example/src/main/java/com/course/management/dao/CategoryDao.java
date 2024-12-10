package com.course.management.dao;

import com.course.management.entities.Category;
import com.course.management.utils.CategoryRowMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Repository
public class CategoryDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void init() {

        String createTableQuery = "create table if not EXISTS categories(id int primary key, title varchar(100) not null, description varchar(500))";
        jdbcTemplate.update(createTableQuery);
        System.out.println("Table created:");

    }

    //create table

    //save category
    public Category save(Category category) {
        //parameterized query
        String query = "insert into categories (id,title,description) values (?,?,?)";
        int rows = jdbcTemplate.update(query, category.getId(), category.getTitle(), category.getDescription());
        System.out.println(rows + "effected");
        return category;
    }


    //update category
    public Category update(int categoryId, Category newCat) {
        String query = "update categories set title= ? , description=? WHERE id = ?";
        int update = jdbcTemplate.update(query, newCat.getTitle(), newCat.getDescription(), categoryId);
        System.out.println("updated " + update);
        newCat.setId(categoryId);
        return newCat;
    }


    // get all category

    public List<Category> getAll() {
        String query = "select * from categories";
//        List<Map<String, Object>> data = jdbcTemplate.queryForList(query);
//        List<Category> categories = jdbcTemplate.queryForList(query, Category.class);
        List<Category> categories = jdbcTemplate.query(query, new CategoryRowMapper());
        return categories;
    }


    // get single category by id


    public Category get(int categoryId) {
        String query = "select * from categories where id = ?";
//        return jdbcTemplate.queryForObject(query, Category.class, categoryId);
        return jdbcTemplate.queryForObject(query, new CategoryRowMapper(), categoryId);
    }


    // delete category

    public void delete(int categoryId) {
        String query = "delete from categories where id =?";
        jdbcTemplate.update(query, categoryId);
    }


}
