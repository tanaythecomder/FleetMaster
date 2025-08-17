package com.example.spring.board.services.core;

import com.example.spring.board.enums.VehicleStatus;
import com.example.spring.board.model.Vehicle;
import com.example.spring.board.repository.VehicleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleCoreService {

    private final VehicleRepository vehicleRepository;

    public Vehicle saveVehicle(Vehicle vehicle){
        try{
            return vehicleRepository.save(vehicle);
        } catch (Exception e) {
            System.out.println("error while saving vehicle");
            throw new RuntimeException(e);
        }
    }

    public List<Vehicle> getAllVehicle(){
        try{
            return vehicleRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public List<Vehicle> getAvailableVehicle(){
        try {
            return vehicleRepository.findAllByStatus(VehicleStatus.AVAILABLE);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Vehicle getVehicleById(Long id){
        return vehicleRepository.findById(id).orElse(null);
    }

    public void deleteVehicle(Long id){
        if (!vehicleRepository.existsById(id)) {
                throw new EntityNotFoundException("Vehicle not found with ID: " + id);
        }
        vehicleRepository.deleteById(id);
    }

    public List<Vehicle> getVehicleByStatus(VehicleStatus status){
        return vehicleRepository.findAllByStatus(status);
    }
}
