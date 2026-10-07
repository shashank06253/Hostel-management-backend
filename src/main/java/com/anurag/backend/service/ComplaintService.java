package com.anurag.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.anurag.backend.entity.Complaint;
import com.anurag.backend.repository.ComplaintRepository;

@Service
public class ComplaintService {

    private final ComplaintRepository complaintRepository;

    public ComplaintService(ComplaintRepository complaintRepository) {
        this.complaintRepository = complaintRepository;
    }

    public List<Complaint> getAllComplaints() {
        return complaintRepository.findAll();
    }

    public Complaint getComplaintById(Long id) {
        return complaintRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Complaint not found with id: " + id));
    }

    public Complaint addComplaint(Complaint complaint) {
        return complaintRepository.save(complaint);
    }

    public Complaint updateComplaint(Long id, Complaint updatedComplaint) {

        Complaint existing = complaintRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Complaint not found with id: " + id));

        existing.setStudentId(updatedComplaint.getStudentId());
        existing.setSubject(updatedComplaint.getSubject());
        existing.setDescription(updatedComplaint.getDescription());
        existing.setStatus(updatedComplaint.getStatus());
        existing.setCreatedDate(updatedComplaint.getCreatedDate());

        return complaintRepository.save(existing);
    }

    public void deleteComplaint(Long id) {

        if (!complaintRepository.existsById(id)) {
            throw new RuntimeException("Complaint not found with id: " + id);
        }

        complaintRepository.deleteById(id);
    }

    public List<Complaint> getComplaintsByStudentId(Long studentId) {
        return complaintRepository.findByStudentId(studentId);
    }
}