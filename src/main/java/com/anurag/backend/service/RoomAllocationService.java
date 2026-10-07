package com.anurag.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.anurag.backend.entity.Room;
import com.anurag.backend.entity.RoomAllocation;
import com.anurag.backend.exception.AllocationNotFoundException;
import com.anurag.backend.exception.BadRequestException;
import com.anurag.backend.repository.RoomAllocationRepository;
import com.anurag.backend.repository.RoomRepository;
import com.anurag.backend.repository.StudentRepository;

@Service
public class RoomAllocationService {

    private final RoomAllocationRepository roomAllocationRepository;
    private final StudentRepository studentRepository;
    private final RoomRepository roomRepository;

    public RoomAllocationService(
            RoomAllocationRepository roomAllocationRepository,
            StudentRepository studentRepository,
            RoomRepository roomRepository) {

        this.roomAllocationRepository = roomAllocationRepository;
        this.studentRepository = studentRepository;
        this.roomRepository = roomRepository;
    }

    public List<RoomAllocation> getAllAllocations() {
        return roomAllocationRepository.findAll();
    }

    public RoomAllocation getAllocationById(Long id) {
        return roomAllocationRepository.findById(id)
                .orElseThrow(() -> new AllocationNotFoundException(id));
    }

    public RoomAllocation allocateRoom(RoomAllocation allocation) {

        // Check whether student exists
studentRepository.findById(allocation.getStudentId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Student not found with id: " + allocation.getStudentId()));

        // Check whether room exists
        Room room = roomRepository.findById(allocation.getRoomId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Room not found with id: " + allocation.getRoomId()));

        // Check whether student is already allocated
        if (roomAllocationRepository
                .findByStudentId(allocation.getStudentId())
                .isPresent()) {

            throw new BadRequestException(
        "Student is already allocated to a room");
        }

        // Check room capacity
        if (room.getOccupied() >= room.getCapacity()) {
            throw new BadRequestException(
                    "Room is full");
        }

        // Increase occupied count
        room.setOccupied(room.getOccupied() + 1);

        // Update room status
        if (room.getOccupied() >= room.getCapacity()) {
            room.setStatus("FULL");
        } else {
            room.setStatus("AVAILABLE");
        }

        roomRepository.save(room);

        return roomAllocationRepository.save(allocation);
    }

    public void deleteAllocation(Long id) {

        RoomAllocation allocation = roomAllocationRepository.findById(id)
                .orElseThrow(() -> new AllocationNotFoundException(id));

        Room room = roomRepository.findById(allocation.getRoomId())
                .orElse(null);

        // Decrease occupied count when student leaves
        if (room != null && room.getOccupied() > 0) {
            room.setOccupied(room.getOccupied() - 1);

            if (room.getOccupied() < room.getCapacity()) {
                room.setStatus("AVAILABLE");
            }

            roomRepository.save(room);
        }

        roomAllocationRepository.deleteById(id);
    }
}