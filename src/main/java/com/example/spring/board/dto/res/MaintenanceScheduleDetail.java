package com.example.spring.board.dto.res;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaintenanceScheduleDetail {
    private Long id;
    private Long vehicleId;
    private Date serviceDate;
    private String serviceDetail;
}

