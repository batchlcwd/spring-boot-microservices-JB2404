package com.jpa.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jpa.example.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
