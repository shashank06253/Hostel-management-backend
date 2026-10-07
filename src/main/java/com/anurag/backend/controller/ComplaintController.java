package com.anurag.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anurag.backend.entity.Complaint;
import com.anurag.backend.service.ComplaintService;

@RestController
@RequestMapping("/api/complaints")
public class ComplaintController {

    private final ComplaintService complaintService;

    public ComplaintController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    @GetMapping
    public List<Complaint> getAllComplaints() {
        return complaintService.getAllComplaints();
    }

    @GetMapping("/{id}")
    public Complaint getComplaintById(@PathVariable Long id) {
        return complaintService.getComplaintById(id);
    }

    @PostMapping
    public Complaint addComplaint(@RequestBody Complaint complaint) {
        return complaintService.addComplaint(complaint);
    }

    @PutMapping("/{id}")
    public Complaint updateComplaint(
            @PathVariable Long id,
            @RequestBody Complaint complaint) {
        return complaintService.updateComplaint(id, complaint);
    }

    @DeleteMapping("/{id}")
    public String deleteComplaint(@PathVariable Long id) {
        complaintService.deleteComplaint(id);
        return "Complaint deleted successfully";
    }

    @GetMapping("/student/{studentId}")
    public List<Complaint> getComplaintsByStudentId(
            @PathVariable Long studentId) {
        return complaintService.getComplaintsByStudentId(studentId);
    }
}