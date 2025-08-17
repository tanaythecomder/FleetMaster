package com.example.spring.board.services;

import com.example.spring.board.dto.req.CreateBookingRequest;
import com.example.spring.board.dto.res.BookingDetail;
import com.example.spring.board.model.Booking;
import com.example.spring.board.services.core.BookingCoreService;
import com.example.spring.board.utils.Mappers;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingCoreService bookingCoreService;

    public String insertBooking(CreateBookingRequest createBookingRequest){
        Booking booking = Booking.builder()
                .startDate(createBookingRequest.getStartDate())
                .endDate(createBookingRequest.getEndDate())
                .build();

        Booking savedBooking = bookingCoreService.saveBooking(booking);
        return savedBooking.getId().toString();
    }

    // IMPORTANT
    public List<BookingDetail> getAllBookings() {
        return bookingCoreService
                .getAllBooking()
                .stream()
                .map(Mappers::bookingDetailFromBooking)
                .toList();
    }

    public BookingDetail getBookingById(Long id){
        try {
            return Mappers.bookingDetailFromBooking(bookingCoreService.getBookingById(id));
        } catch(EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    public Booking deleteBookingById(Long id){
        try {
            Booking booking = bookingCoreService.getBookingById(id);
            bookingCoreService.deleteBookingById(id);
            return booking;
        } catch(EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}