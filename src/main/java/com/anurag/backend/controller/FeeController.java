package com.anurag.backend.controller;

import com.anurag.backend.entity.Fee;
import com.anurag.backend.service.FeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fees")
public class FeeController {

    private final FeeService feeService;

    public FeeController(FeeService feeService) {
        this.feeService = feeService;
    }

    @GetMapping
    public List<Fee> getAllFees() {
        return feeService.getAllFees();
    }

    @GetMapping("/{id}")
    public Fee getFeeById(@PathVariable Long id) {
        return feeService.getFeeById(id);
    }

    @PostMapping
    public Fee addFee(@RequestBody Fee fee) {
        return feeService.addFee(fee);
    }

    @PutMapping("/{id}")
    public Fee updateFee(
            @PathVariable Long id,
            @RequestBody Fee fee) {
        return feeService.updateFee(id, fee);
    }

    @DeleteMapping("/{id}")
    public String deleteFee(@PathVariable Long id) {
        feeService.deleteFee(id);
        return "Fee deleted successfully";
    }

    @GetMapping("/student/{studentId}")
    public List<Fee> getFeesByStudentId(
            @PathVariable Long studentId) {
        return feeService.getFeesByStudentId(studentId);
    }
}