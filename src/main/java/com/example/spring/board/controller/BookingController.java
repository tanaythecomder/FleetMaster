package com.example.spring.board.controller;


import com.example.spring.board.dto.req.CreateBookingRequest;
import com.example.spring.board.services.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@Controller
@RequiredArgsConstructor
@RequestMapping("/booking")

public class BookingController {
    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<String> insertBooking(@RequestBody @Valid CreateBookingRequest createBookingRequest) {
        return ResponseEntity.ok(bookingService.insertBooking(createBookingRequest));
    }

    @GetMapping
    public ResponseEntity<?> getAllBooking() {
        return ResponseEntity.ok(bookingService.getAllBookings());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookingById(@PathVariable Long id) {
        return ResponseEntity.ok(bookingService.getBookingById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBooking(@PathVariable Long id) {
        return ResponseEntity.ok(bookingService.deleteBookingById(id));
    }
}
