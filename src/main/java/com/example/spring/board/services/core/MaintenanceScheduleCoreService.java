package com.example.spring.board.services.core;

import com.example.spring.board.model.MaintenanceSchedule;
import com.example.spring.board.repository.MaintenanceScheduleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MaintenanceScheduleCoreService {
    private final MaintenanceScheduleRepository maintenanceScheduleRepository;

    public MaintenanceSchedule saveSchedule(MaintenanceSchedule schedule){
        return maintenanceScheduleRepository.save(schedule);
    }

    public MaintenanceSchedule getScheduleByIdAndVehicleId(Long id, Long vehicleId){
           return maintenanceScheduleRepository.findByIdAndVehicleId(id, vehicleId).orElse(null);
    }

    public void deleteScheduleByVehicleId(Long vehicleId, Long id){
           maintenanceScheduleRepository.deleteByIdAndVehicleId(vehicleId, id);
    }

    public void deleteScheduleById(Long id){
           maintenanceScheduleRepository.deleteById(id);
    }

    public List<MaintenanceSchedule> getScheduleByVehicleId(Long vehicleId){
            return maintenanceScheduleRepository.findAllByVehicleId(vehicleId);
    }

    public MaintenanceSchedule getScheduleById(Long id){
        return maintenanceScheduleRepository.findById(id).orElse(null);
    }
}
