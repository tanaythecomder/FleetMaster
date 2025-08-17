package com.example.spring.board.services;

import com.example.spring.board.dto.res.VehicleBookingDetail;
import com.example.spring.board.model.Booking;
import com.example.spring.board.model.Vehicle;
import com.example.spring.board.model.VehicleBooking;
import com.example.spring.board.services.core.BookingCoreService;
import com.example.spring.board.services.core.VehicleBookingCoreService;
import com.example.spring.board.services.core.VehicleCoreService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;


@Service
@RequiredArgsConstructor
public class VehicleBookingService {
    private final VehicleBookingCoreService vehicleBookingCoreService;
    private final VehicleCoreService vehicleCoreService;
    private final BookingCoreService bookingCoreService;

    public String insertVehicleBooking(VehicleBookingDetail vehicleBookingDetail) {
        try {
            Long vehicleId = vehicleBookingDetail.getVehicleId();
            Vehicle vehicle = vehicleCoreService.getVehicleById(vehicleId);
            if(Objects.isNull(vehicle)){
                throw new EntityNotFoundException("vehicle not found for this id: " + vehicleId);
            }
            Long bookingId = vehicleBookingDetail.getBookingId();
            Booking booking = bookingCoreService.getBookingById(bookingId);
            if(Objects.isNull(booking)){
                throw new EntityNotFoundException("booking not found for this id: " + bookingId);
            }

            VehicleBooking vehicleBooking = VehicleBooking.builder()
                    .vehicleId(vehicleId)
                    .bookingId(bookingId)
                    .build();

            VehicleBooking savedVehicleBooking = vehicleBookingCoreService.saveVehicleBooking(vehicleBooking);
            System.out.println(savedVehicleBooking);
            return "";
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}

