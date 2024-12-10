package com.course.management.utils;

import com.course.management.entities.Category;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryRowMapper implements RowMapper<Category> {


    //logic to convert table row to category object:
    @Override
    public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
        //object category: blank
        Category category = new Category();
        // table se data nakalna hai aur category mein set karna hai.
        category.setId(rs.getInt("id"));
        category.setTitle(rs.getString("title"));
        category.setDescription(rs.getString("description"));
        return category;
    }


}
