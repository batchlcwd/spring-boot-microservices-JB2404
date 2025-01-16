package com.result.view.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "result_viewer_marks")
public class Mark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String subjectName;
    private String marks;
    private String maxMarks;
    private String feedback;
    private String grade;

    @ManyToOne
    private  Student student;

}
