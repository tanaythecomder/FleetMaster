package com.example.spring.board.services;

import com.example.spring.board.dto.res.VehicleTypeDetail;
import com.example.spring.board.model.VehicleType;
import com.example.spring.board.services.core.VehicleTypeCoreService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class VehicleTypeService {
    private final VehicleTypeCoreService vehicleTypeCoreService;

    public String insertVehicleType(@RequestBody VehicleTypeDetail vehicleTypeDetail){
        VehicleType vehicleType = VehicleType.builder()
                                    .typeName(vehicleTypeDetail.getType())
                                    .build();

        VehicleType savedVehicleType = vehicleTypeCoreService.saveVehicleType(vehicleType);
        return savedVehicleType.getId().toString();
    }

    public VehicleTypeDetail getVehicleById(Long id){
        try {
            VehicleType vehicleType = vehicleTypeCoreService.getVehicleTypeById(id);
            if(vehicleType == null){
                throw new EntityNotFoundException("vehicle type not found for this id: "+ id);
            }
            return new VehicleTypeDetail(
                    vehicleType.getId(),
                    vehicleType.getTypeName()
            );
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }


    public VehicleType deleteVehicleTypeById(Long id){
        try {
            VehicleType vehicleType = vehicleTypeCoreService.getVehicleTypeById(id);

            if(Objects.isNull(vehicleType)){
                throw new EntityNotFoundException("vehicle-type is not found id: "+ id);
            }
            vehicleTypeCoreService.deleteVehicleTypeById(id);
            return vehicleType;
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    public List<VehicleTypeDetail> getAllVehicleType(){
        List<VehicleType> vehicleTypes = vehicleTypeCoreService.getAllVehicleTypes();
        List<VehicleTypeDetail> vehicleTypeDetails = new ArrayList<>();

        for(VehicleType vt : vehicleTypes){
            vehicleTypeDetails.add(new VehicleTypeDetail(
                    vt.getId(),
                    vt.getTypeName()
            ));
        }
        return vehicleTypeDetails;
    }
}
