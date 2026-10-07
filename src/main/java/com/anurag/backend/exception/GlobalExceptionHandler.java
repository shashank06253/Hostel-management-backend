package com.anurag.backend.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(StudentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleStudentNotFound(StudentNotFoundException ex) {
        return Map.of(
                "error", "Student Not Found",
                "message", ex.getMessage()
        );
    }

    @ExceptionHandler(AllocationNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleAllocationNotFound(AllocationNotFoundException ex) {
        return Map.of(
                "error", "Allocation Not Found",
                "message", ex.getMessage()
        );
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleBadRequest(BadRequestException ex) {
        return Map.of(
                "error", "Bad Request",
                "message", ex.getMessage()
        );
    }
}