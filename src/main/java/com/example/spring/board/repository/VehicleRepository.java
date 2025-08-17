package com.example.spring.board.repository;

import com.example.spring.board.enums.VehicleStatus;
import com.example.spring.board.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
     List<Vehicle> findAllByStatus(VehicleStatus status);

}
