package com.anurag.backend.exception;

public class AllocationNotFoundException extends RuntimeException {

    public AllocationNotFoundException(Long id) {
        super("Room allocation not found with id: " + id);
    }
}