package com.example.spring.board.dto.res;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDetail {
    private Long vehicleId;
    private String model;
    private int manufactureYear;
    private String status;
    private String typeName;
}



