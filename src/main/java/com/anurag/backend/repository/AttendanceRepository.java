package com.anurag.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anurag.backend.entity.Attendance;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    List<Attendance> findByStudentId(Long studentId);
}