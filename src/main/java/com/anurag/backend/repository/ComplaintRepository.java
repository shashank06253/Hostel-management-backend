package com.anurag.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anurag.backend.entity.Complaint;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

    List<Complaint> findByStudentId(Long studentId);
}