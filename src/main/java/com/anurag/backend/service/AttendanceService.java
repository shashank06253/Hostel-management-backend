package com.anurag.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.anurag.backend.entity.Attendance;
import com.anurag.backend.repository.AttendanceRepository;

@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;

    public AttendanceService(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    public List<Attendance> getAllAttendance() {
        return attendanceRepository.findAll();
    }

    public Attendance getAttendanceById(Long id) {
        return attendanceRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Attendance not found with id: " + id));
    }

    public Attendance addAttendance(Attendance attendance) {
        return attendanceRepository.save(attendance);
    }

    public Attendance updateAttendance(Long id, Attendance updatedAttendance) {

        Attendance existing = attendanceRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Attendance not found with id: " + id));

        existing.setStudentId(updatedAttendance.getStudentId());
        existing.setDate(updatedAttendance.getDate());
        existing.setStatus(updatedAttendance.getStatus());

        return attendanceRepository.save(existing);
    }

    public void deleteAttendance(Long id) {

        if (!attendanceRepository.existsById(id)) {
            throw new RuntimeException("Attendance not found with id: " + id);
        }

        attendanceRepository.deleteById(id);
    }

    public List<Attendance> getAttendanceByStudentId(Long studentId) {
        return attendanceRepository.findByStudentId(studentId);
    }
}