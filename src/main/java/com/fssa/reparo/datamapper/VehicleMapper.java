package com.fssa.reparo.datamapper;

import com.fssa.reparo.dto.user.UserResponseDto;
import com.fssa.reparo.dto.vehicle.VehicleRequestDto;
import com.fssa.reparo.dto.vehicle.VehicleResponseDto;
import com.fssa.reparo.model.Vehicle;

public class VehicleMapper {


    /**
     * Maps a VehicleRequestDto to a Vehicle object.
     *
     * @param vehicleDto The VehicleRequestDto to be mapped.
     * @return A Vehicle object with the mapped values.
     */
    public Vehicle mapVehicleRequestDtoToVehicle(VehicleRequestDto vehicleDto ){
        Vehicle vehicle = new Vehicle();
        vehicle.setUserId(vehicleDto.getUserId());
        vehicle.setVehicleCompany(vehicleDto.getCompany());
        vehicle.setVehicleModel(vehicleDto.getModel());
        vehicle.setVehicleType(vehicleDto.getType());
        vehicle.setVehicleYear(vehicleDto.getYear());
        vehicle.setVehicleNumber(vehicleDto.getVehicleNumber());
        return vehicle;
    }
    /**
     * Maps a Vehicle object to a VehicleResponseDto.
     *
     * @param vehicle The Vehicle object to be mapped.
     * @return A VehicleResponseDto with the mapped values.
     */
    public VehicleResponseDto mapVehicleToResponseDto(Vehicle vehicle){
        UserResponseDto userResponseDto = new UserResponseDto(vehicle.getUser().getId(),vehicle.getUser().getName(),vehicle.getUser().getNumber());
        VehicleRequestDto vehicleRequestDto = new VehicleRequestDto(vehicle.getVehicleCompany(), vehicle.getVehicleModel(), vehicle.getVehicleNumber(), vehicle.getVehicleType(), vehicle.getVehicleYear());
        return new VehicleResponseDto(userResponseDto,vehicleRequestDto,vehicle.getVehicleId());

    }

}
