package com.example.spring.board.dto.req;

import jakarta.validation.constraints.FutureOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookingRequest {

    @FutureOrPresent(message = "start date cannot be past")
    private Date startDate;

    @FutureOrPresent(message = "end date cannot be in past")
    private Date endDate;
}


