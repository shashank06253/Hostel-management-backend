package com.anurag.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anurag.backend.entity.RoomAllocation;

public interface RoomAllocationRepository extends JpaRepository<RoomAllocation, Long> {

    Optional<RoomAllocation> findByStudentId(Long studentId);

}