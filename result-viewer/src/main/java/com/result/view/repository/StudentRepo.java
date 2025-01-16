package com.result.view.repository;

import com.result.view.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {


    //important
    Optional<Student> findByRollNumber(String rollNumber);

    Optional<Student> findByRollNumberAndDateOfBirth(String rollNumber, LocalDate dateOfBirth);
}
