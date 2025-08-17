package com.example.spring.board.utils;

import com.example.spring.board.dto.res.BookingDetail;
import com.example.spring.board.model.Booking;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Mappers {
    public BookingDetail bookingDetailFromBooking(Booking booking) {
        return new BookingDetail(booking.getId(), booking.getStartDate(), booking.getEndDate());
    }
}
