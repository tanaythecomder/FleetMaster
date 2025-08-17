package com.example.spring.board.repository;

import com.example.spring.board.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
//    Booking getByVehicleId(Long Id);
}
