package com.example.spring.board.dto.req;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateVehicleMaintenanceSchedule {

    @NotNull(message = "service detail should have to provide")
    private String serviceDetail;
}

