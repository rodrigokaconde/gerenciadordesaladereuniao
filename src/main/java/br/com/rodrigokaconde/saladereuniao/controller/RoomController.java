package br.com.rodrigokaconde.saladereuniao.controller;

import br.com.rodrigokaconde.saladereuniao.exception.ResourceNotFoundException;
import br.com.rodrigokaconde.saladereuniao.model.Room;
import br.com.rodrigokaconde.saladereuniao.repository.RoomRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin(origins = "http://localhost:4200")//anotação que front vai consumir e o local
@RequestMapping("/api/v1")
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;

    @GetMapping("/rooms")
    public List<Room> getAllRooms(){
        return roomRepository.findAll();
    }

    @GetMapping("/rooms/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable(value = "id") long roomId)
        throws ResourceNotFoundException{
        Room romm = roomRepository.findById(roomId)
                .orElseThrow(()-> new ResourceNotFoundException("Room not found:: "+roomId));
        return ResponseEntity.ok().body(romm);
    }

    @PostMapping("/rooms")
    public Room createRoom(@Valid @RequestBody Room room){
        return roomRepository.save(room);
    }

    @PutMapping("/rooms/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable(value = "id") long roomId,
                                           @Valid @RequestBody Room roomDetails) throws ResourceNotFoundException{
        Room room = roomRepository.findById(roomId)
                .orElseThrow(()-> new ResourceNotFoundException("Room not found for this id:: "+roomId));
        room.setName(roomDetails.getName());
        room.setDate(roomDetails.getDate());
        room.setEndHour(roomDetails.getEndHour());
        room.setStartHour(roomDetails.getStartHour());
        final Room updateRoom = roomRepository.save(room);
        return ResponseEntity.ok(updateRoom);
    }

    @DeleteMapping("/rooms/{id}")
    public Map<String, Boolean> deleteRoom(@PathVariable(value = "id") long roomid)
        throws ResourceNotFoundException{
        Room room = roomRepository.findById(roomid)
                .orElseThrow(()-> new ResourceNotFoundException("Room not found for this id: "+roomid));
        roomRepository.delete(room);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
