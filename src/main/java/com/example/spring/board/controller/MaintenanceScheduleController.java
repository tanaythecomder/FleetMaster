package com.example.spring.board.controller;

import com.example.spring.board.dto.req.CreateMaintenanceSchedule;
import com.example.spring.board.dto.req.UpdateVehicleMaintenanceSchedule;
import com.example.spring.board.services.MaintenanceScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
@RequestMapping("/maintenance-schedule")
public class MaintenanceScheduleController {

    private final MaintenanceScheduleService maintenanceScheduleService;

    @PostMapping()
    public ResponseEntity<String> insertMaintenanceSchedule(@RequestBody @Valid CreateMaintenanceSchedule createMaintenanceSchedule){
        return ResponseEntity.ok(maintenanceScheduleService.insertSchedule(createMaintenanceSchedule));
    }


    @PutMapping("/{vehicleId}/{id}")
    public ResponseEntity<String> updateMaintenanceById(@PathVariable Long vehicleId, @PathVariable Long id, @RequestBody UpdateVehicleMaintenanceSchedule updateVehicleMaintenanceSchedule){
        return ResponseEntity.ok(maintenanceScheduleService.updateSchedule(vehicleId, id, updateVehicleMaintenanceSchedule));
    }

    //testing is not done for this, having issues
    @DeleteMapping("/{vehicleId}/{id}")
    public ResponseEntity<?> deleteMaintenanceByBothId(@PathVariable Long vehicleId, @PathVariable Long id){
        return ResponseEntity.ok(maintenanceScheduleService.deleteScheduleByVehicleId(vehicleId, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?>deleteScheduleById(@PathVariable Long id){
        return ResponseEntity.ok(maintenanceScheduleService.deleteScheduleById(id));
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getMaintenanceScheduleById(@PathVariable Long id) {
        return ResponseEntity.ok(maintenanceScheduleService.getScheduleById(id));
    }

    @GetMapping("/get-by-vehicleId/{vehicleId}")
    public ResponseEntity<?> getSchedulesByVehicle(@PathVariable Long vehicleId) {
        return ResponseEntity.ok(maintenanceScheduleService.getByVehicleId(vehicleId));
    }
}
