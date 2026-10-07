package com.anurag.backend.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anurag.backend.repository.AttendanceRepository;
import com.anurag.backend.repository.ComplaintRepository;
import com.anurag.backend.repository.FeeRepository;
import com.anurag.backend.repository.RoomAllocationRepository;
import com.anurag.backend.repository.RoomRepository;
import com.anurag.backend.repository.StudentRepository;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final StudentRepository studentRepository;
    private final RoomRepository roomRepository;
    private final RoomAllocationRepository roomAllocationRepository;
    private final FeeRepository feeRepository;
    private final ComplaintRepository complaintRepository;
    private final AttendanceRepository attendanceRepository;

    public DashboardController(
            StudentRepository studentRepository,
            RoomRepository roomRepository,
            RoomAllocationRepository roomAllocationRepository,
            FeeRepository feeRepository,
            ComplaintRepository complaintRepository,
            AttendanceRepository attendanceRepository) {

        this.studentRepository = studentRepository;
        this.roomRepository = roomRepository;
        this.roomAllocationRepository = roomAllocationRepository;
        this.feeRepository = feeRepository;
        this.complaintRepository = complaintRepository;
        this.attendanceRepository = attendanceRepository;
    }

    @GetMapping("/summary")
    public Map<String, Long> getDashboardSummary() {

        Map<String, Long> summary = new HashMap<>();

        summary.put("totalStudents", studentRepository.count());
        summary.put("totalRooms", roomRepository.count());
        summary.put("totalAllocations", roomAllocationRepository.count());
        summary.put("totalFees", feeRepository.count());
        summary.put("totalComplaints", complaintRepository.count());
        summary.put("totalAttendanceRecords", attendanceRepository.count());

        return summary;
    }
}