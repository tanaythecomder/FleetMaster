package com.example.spring.board.dto.req;

import com.example.spring.board.enums.VehicleStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateVehicle {

    private String model;

    @Positive(message = "manufacture year cannot be negative")
    private int manufactureYear;

    @NotNull(message = "status should not be null")
    private VehicleStatus status;

    @NotNull(message = "type id cannot be null")
    private Long typeId;
}
