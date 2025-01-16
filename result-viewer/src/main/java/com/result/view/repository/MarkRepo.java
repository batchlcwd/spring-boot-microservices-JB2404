package com.result.view.repository;

import com.result.view.entity.Mark;
import com.result.view.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarkRepo extends JpaRepository<Mark, Long>
{

    List<Mark> findByStudent(Student student);

}
