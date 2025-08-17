package com.example.spring.board.services.core;

import com.example.spring.board.model.Booking;
import com.example.spring.board.repository.BookingRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class BookingCoreService {
    private final BookingRepository bookingRepository;

    public Booking saveBooking(Booking booking){
        return bookingRepository.save(booking);
    }

    public List<Booking> getAllBooking(){
        return bookingRepository.findAll();
    }


    /*
        * class A {}
        class B extends A {}
        class C extends B {}

        try {}
        catch(A e) {}
        catch(B e) {}
        catch(C e) {}
    * */
    public Booking getBookingById(Long id) {
        Supplier<EntityNotFoundException> orElseHandler = () ->
                new EntityNotFoundException("booking not found for this id: " + id);
        return bookingRepository.findById(id).orElseThrow(orElseHandler);
    }

    public void deleteBookingById(Long id){
        bookingRepository.deleteById(id);
    }
}

