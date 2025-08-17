package com.example.spring.board.repository;

import com.example.spring.board.model.MaintenanceSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MaintenanceScheduleRepository extends JpaRepository<MaintenanceSchedule, Long> {
    List<MaintenanceSchedule> findAllByVehicleId(Long VehicleId);
    Optional<MaintenanceSchedule> findByIdAndVehicleId(Long id, Long vehicleId);
    void deleteByIdAndVehicleId(Long vehicleId, Long id);
}

