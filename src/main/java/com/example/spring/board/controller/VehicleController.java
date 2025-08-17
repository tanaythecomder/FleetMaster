package com.example.spring.board.controller;

import com.example.spring.board.dto.req.CreateVehicle;
import com.example.spring.board.dto.req.UpdateVehicleStatus;
import com.example.spring.board.enums.VehicleStatus;
import com.example.spring.board.services.VehicleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/vehicle")
public class VehicleController {
    private final VehicleService vehicleService;
    @PostMapping()
    public ResponseEntity<String> insertVehicle(@RequestBody @Valid CreateVehicle createVehicle){
        try{
            return ResponseEntity.ok(vehicleService.insertVehicle(createVehicle));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateVehicleStatus(@RequestBody @Valid UpdateVehicleStatus updateVehicleStatus, @PathVariable Long id){
        return ResponseEntity.ok(vehicleService.updateVehicleStatus(updateVehicleStatus, id));
    }

    @GetMapping()
    public ResponseEntity<?> getAllVehicle(){
        try{
            return ResponseEntity.ok(vehicleService.getAllVehicle());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getVehicleById(@PathVariable Long id){
        return ResponseEntity.ok(vehicleService.getVehicleById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVehicleById(@PathVariable Long id){
        return ResponseEntity.ok(vehicleService.deleteVehicleById(id));
    }

    @GetMapping("/by-status")
    public ResponseEntity<?> getVehiclesByStatus(@RequestParam("status") VehicleStatus status) {
        return ResponseEntity.ok(vehicleService.getVehicleByStatus(status));
    }
}
