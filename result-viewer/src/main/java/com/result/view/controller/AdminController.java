package com.result.view.controller;

import com.result.view.dto.StudentForm;
import com.result.view.entity.Student;
import com.result.view.repository.StudentRepo;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private StudentRepo studentRepo;
    private ModelMapper modelMapper;

    public AdminController(StudentRepo studentRepo, ModelMapper modelMapper) {
        this.studentRepo = studentRepo;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add-result")
    public String addResultForm(Model model) {
        StudentForm studentForm = new StudentForm();

        List<String> standardOptions = new ArrayList<>();
        standardOptions.add("CLASS 1");
        standardOptions.add("CLASS 2");
        standardOptions.add("CLASS 3");
        standardOptions.add("CLASS 4");
        standardOptions.add("CLASS 5");
        standardOptions.add("CLASS 6");


        model.addAttribute("studentForm", studentForm);
        model.addAttribute("standardOptions", standardOptions);
        return "admin/add_result";
    }

    @RequestMapping(value = "/add-result-action", method = RequestMethod.POST)
    public String processAddResultForm(
            @Valid @ModelAttribute StudentForm studentForm,
            BindingResult bindingResult,
            Model model
    ) {

        if (bindingResult.hasErrors()) {
            List<String> standardOptions = new ArrayList<>();
            standardOptions.add("CLASS 1");
            standardOptions.add("CLASS 2");
            standardOptions.add("CLASS 3");
            standardOptions.add("CLASS 4");
            standardOptions.add("CLASS 5");
            standardOptions.add("CLASS 6");
            model.addAttribute("standardOptions", standardOptions);
            return "admin/add_result";
        }


//        convert student form to student entity

        Student student = modelMapper.map(studentForm, Student.class);
        student.setId(UUID.randomUUID().toString());
        Student savedStudent = studentRepo.save(student);
        return "redirect:/admin/add-result?message=Student added successfully ";

    }
}
