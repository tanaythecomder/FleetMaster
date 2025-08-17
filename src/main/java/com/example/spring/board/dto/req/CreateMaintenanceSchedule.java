package com.example.spring.board.dto.req;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class CreateMaintenanceSchedule {
    @NotNull(message = "vehicle id have to provide")
    private Long vehicleId;

    @FutureOrPresent(message = "service data cannot be in past")
    private Date serviceDate;

    @NotNull(message = "service detail should be provided")
    private String serviceDetail;
}

