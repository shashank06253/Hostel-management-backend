package com.anurag.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anurag.backend.entity.RoomAllocation;
import com.anurag.backend.service.RoomAllocationService;

@RestController
@RequestMapping("/api/allocations")
public class RoomAllocationController {

    private final RoomAllocationService roomAllocationService;

    public RoomAllocationController(RoomAllocationService roomAllocationService) {
        this.roomAllocationService = roomAllocationService;
    }

    @GetMapping
    public List<RoomAllocation> getAllAllocations() {
        return roomAllocationService.getAllAllocations();
    }

    @PostMapping
    public RoomAllocation allocateRoom(@RequestBody RoomAllocation allocation) {
        return roomAllocationService.allocateRoom(allocation);
    }

    @GetMapping("/{id}")
    public RoomAllocation getAllocationById(@PathVariable Long id) {
        return roomAllocationService.getAllocationById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteAllocation(@PathVariable Long id) {
        roomAllocationService.deleteAllocation(id);
        return "Room allocation deleted successfully";
    }
}