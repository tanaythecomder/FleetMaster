package com.example.spring.board.services;

import com.example.spring.board.dto.req.CreateMaintenanceSchedule;
import com.example.spring.board.dto.req.UpdateVehicleMaintenanceSchedule;
import com.example.spring.board.dto.res.MaintenanceScheduleDetail;
import com.example.spring.board.model.MaintenanceSchedule;
import com.example.spring.board.services.core.MaintenanceScheduleCoreService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MaintenanceScheduleService {
    private final MaintenanceScheduleCoreService maintenanceScheduleCoreService;

    public String insertSchedule(@RequestBody CreateMaintenanceSchedule createMaintenanceSchedule){
        MaintenanceSchedule maintenanceSchedule = MaintenanceSchedule.builder()
                .serviceDate(createMaintenanceSchedule.getServiceDate())
                .serviceDetail(createMaintenanceSchedule.getServiceDetail())
                .vehicleId(createMaintenanceSchedule.getVehicleId())
                .build();

        MaintenanceSchedule savedSchedule = maintenanceScheduleCoreService.saveSchedule(maintenanceSchedule);
        return savedSchedule.getId().toString();
    }

    public String updateSchedule(Long vehicleId, Long id, UpdateVehicleMaintenanceSchedule schedule){
        try {
            MaintenanceSchedule maintenanceSchedule = maintenanceScheduleCoreService.getScheduleByIdAndVehicleId(id, vehicleId);
            if(maintenanceSchedule == null){
                throw new EntityNotFoundException("schedule not found for this");
            }
            maintenanceSchedule.setServiceDetail(schedule.getServiceDetail());
            maintenanceScheduleCoreService.saveSchedule(maintenanceSchedule);
            return maintenanceSchedule.getId().toString();
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    public String updateScheduleById(Long id, UpdateVehicleMaintenanceSchedule schedule){
        try{
            MaintenanceSchedule existingSchedule = getMaintenanceScheduleById(id);
            existingSchedule.setServiceDetail(schedule.getServiceDetail());
            return maintenanceScheduleCoreService.saveSchedule(existingSchedule).getId().toString();
        }
        catch(EntityNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public MaintenanceSchedule deleteScheduleByVehicleId(Long vehicleId, Long id){
        try {
            MaintenanceSchedule maintenanceSchedule = maintenanceScheduleCoreService.getScheduleByIdAndVehicleId(id, vehicleId);
            if(maintenanceSchedule == null){
                throw new EntityNotFoundException("schedule not found");
            }
            maintenanceScheduleCoreService.deleteScheduleByVehicleId(vehicleId, id);
            return maintenanceSchedule;
        }
        catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }


    public MaintenanceScheduleDetail getScheduleById(Long id){
        try {
            MaintenanceSchedule maintenanceSchedule = getMaintenanceScheduleById(id);
            return new MaintenanceScheduleDetail(
                    maintenanceSchedule.getId(),
                    maintenanceSchedule.getVehicleId(),
                    maintenanceSchedule.getServiceDate(),
                    maintenanceSchedule.getServiceDetail()
            );
        }
        catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    public List<MaintenanceScheduleDetail> getByVehicleId(Long vehicleId){
        List<MaintenanceSchedule> maintenanceSchedules =  maintenanceScheduleCoreService.getScheduleByVehicleId(vehicleId);
        List<MaintenanceScheduleDetail> maintenanceScheduleDetails = new ArrayList<>();

        for(MaintenanceSchedule m : maintenanceSchedules){
            maintenanceScheduleDetails.add(new MaintenanceScheduleDetail(
                    m.getId(),
                    m.getVehicleId(),
                    m.getServiceDate(),
                    m.getServiceDetail()
            ));
        }
        return maintenanceScheduleDetails;
    }

    public MaintenanceSchedule deleteScheduleById(Long id){
        try {
            MaintenanceSchedule existingSchedule = getMaintenanceScheduleById(id);
            maintenanceScheduleCoreService.deleteScheduleById(id);
            return existingSchedule;
        }
        catch (EntityNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    private MaintenanceSchedule getMaintenanceScheduleById(Long id){
        MaintenanceSchedule existingSchedule = maintenanceScheduleCoreService.getScheduleById(id);
        if(existingSchedule == null){
            throw new EntityNotFoundException("schedule not found for this id: "+id);
        }

        return existingSchedule;
    }
}
