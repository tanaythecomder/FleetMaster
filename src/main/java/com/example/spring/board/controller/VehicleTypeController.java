package com.example.spring.board.controller;

import com.example.spring.board.dto.res.VehicleTypeDetail;
import com.example.spring.board.services.VehicleTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/vehicle-type")
public class VehicleTypeController {
    private final VehicleTypeService vehicleTypeService;
    @PostMapping()
    public ResponseEntity<String> insertVehicleType(@RequestBody @Valid VehicleTypeDetail vehicleTypeDetail){
        try{
            return ResponseEntity.ok(vehicleTypeService.insertVehicleType(vehicleTypeDetail));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getVehicleTypeById(@PathVariable Long id){
        return ResponseEntity.ok(vehicleTypeService.getVehicleById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVehicleType(@PathVariable Long id) {
           return ResponseEntity.ok(vehicleTypeService.deleteVehicleTypeById(id));
    }

    @GetMapping()
    public ResponseEntity<?> getAllVehicleType(){
        try {
            return ResponseEntity.ok(vehicleTypeService.getAllVehicleType());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }
}
