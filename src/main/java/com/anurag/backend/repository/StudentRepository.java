package com.anurag.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anurag.backend.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}