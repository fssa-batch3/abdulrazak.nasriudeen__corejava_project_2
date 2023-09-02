package com.fssa.reparo.dto.vehicle;


import com.fssa.reparo.dto.user.UserDTO;

public class VehicleDTO {
    private int vehicleId;

    public VehicleDTO(int vehicleId){
        this.vehicleId =  vehicleId;

    }
    public VehicleDTO(){

    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

}
