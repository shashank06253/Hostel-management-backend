package com.anurag.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.anurag.backend.entity.Room;
import com.anurag.backend.repository.RoomRepository;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Room getRoomById(Long id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found with id: " + id));
    }

    public Room addRoom(Room room) {
        return roomRepository.save(room);
    }

    public Room updateRoom(Long id, Room updatedRoom) {

        Room existingRoom = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found with id: " + id));

        existingRoom.setRoomNumber(updatedRoom.getRoomNumber());
        existingRoom.setBlock(updatedRoom.getBlock());
        existingRoom.setFloor(updatedRoom.getFloor());
        existingRoom.setCapacity(updatedRoom.getCapacity());
        existingRoom.setOccupied(updatedRoom.getOccupied());
        existingRoom.setStatus(updatedRoom.getStatus());

        return roomRepository.save(existingRoom);
    }

    public void deleteRoom(Long id) {

        if (!roomRepository.existsById(id)) {
            throw new RuntimeException("Room not found with id: " + id);
        }

        roomRepository.deleteById(id);
    }
}