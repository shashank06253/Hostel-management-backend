package com.anurag.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.anurag.backend.entity.Fee;
import com.anurag.backend.repository.FeeRepository;

@Service
public class FeeService {

    private final FeeRepository feeRepository;

    public FeeService(FeeRepository feeRepository) {
        this.feeRepository = feeRepository;
    }

    public List<Fee> getAllFees() {
        return feeRepository.findAll();
    }

    public Fee getFeeById(Long id) {
        return feeRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Fee not found with id: " + id));
    }

    public Fee addFee(Fee fee) {
        return feeRepository.save(fee);
    }

    public Fee updateFee(Long id, Fee updatedFee) {

        Fee existingFee = feeRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Fee not found with id: " + id));

        existingFee.setStudentId(updatedFee.getStudentId());
        existingFee.setAmount(updatedFee.getAmount());
        existingFee.setFeeType(updatedFee.getFeeType());
        existingFee.setPaymentDate(updatedFee.getPaymentDate());
        existingFee.setStatus(updatedFee.getStatus());

        return feeRepository.save(existingFee);
    }

    public void deleteFee(Long id) {

        if (!feeRepository.existsById(id)) {
            throw new RuntimeException("Fee not found with id: " + id);
        }

        feeRepository.deleteById(id);
    }

    public List<Fee> getFeesByStudentId(Long studentId) {
        return feeRepository.findByStudentId(studentId);
    }
}