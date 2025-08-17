package com.example.spring.board.dto.res;

import com.example.spring.board.model.Booking;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDetail {
    private Long id;
    private Date startDate;
    private Date endDate;
//
//    // These are called mappers
//    public static BookingDetail from(Booking booking) {
//        return new BookingDetail(booking.getId(), booking.getStartDate(), booking.getEndDate());
//    }
}
