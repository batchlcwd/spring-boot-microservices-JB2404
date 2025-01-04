package com.substring.foodie.controller.example;

import com.substring.foodie.playload.example.Department;
import com.substring.foodie.playload.example.DummyDataGenerator;
import com.substring.foodie.playload.example.Student;
import com.substring.foodie.playload.example.Subject;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    // single student get karne ka ek url

    @RequestMapping("/single")
    public Student getStudent() {

        //created the department
        Department department = new Department();
        department.setDepartmentCode("CS");
        department.setDepartmentName("Computer Science");


        //subject : Data structure
        Subject subject1 = new Subject();
        subject1.setSubjectCode("KCS251");
        subject1.setTitle("Data Structure");
        Subject subject2 = new Subject();
        subject2.setSubjectCode("CS252");
        subject2.setTitle("Database Management System");

        List<Subject> subjects = new ArrayList<>();
        subjects.add(subject1);
        subjects.add(subject2);


        Student student = new Student();
        student.setName("Akshansh Sharma");
        student.setAge(22);
        student.setDepartment(department);
        student.setSubjects(subjects);

        return student;


    }


    @GetMapping("/all")
    public List<Student> getStudents() {
        List<Student> students = DummyDataGenerator.generateDummyStudents();
        return students;


    }

    @RequestMapping("/wish/{message}/for/{userName}")
    public String wish(@PathVariable(value = "message", required = false) String userMessage, @PathVariable(required = false) String userName) {
        return "Wishing " + userMessage + " for " + userName;
    }

    @RequestMapping("/wish")
    public String wishQuery(@RequestParam(value = "message", required = false, defaultValue = "Happy New Year") String userMessage, @RequestParam String userName) {
        return "Wishing " + userMessage + " for " + userName;
    }


}
