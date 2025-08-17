package com.example.spring.board.dto.res;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleBookingDetail {
    private Long vehicleId;
    private Long bookingId;
}


