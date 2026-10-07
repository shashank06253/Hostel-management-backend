package com.anurag.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anurag.backend.entity.Fee;

public interface FeeRepository extends JpaRepository<Fee, Long> {

    List<Fee> findByStudentId(Long studentId);
}