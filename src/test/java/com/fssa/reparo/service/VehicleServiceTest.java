package com.fssa.reparo.service;
import com.fssa.reparo.exception.ServiceException;
import com.fssa.reparo.model.User;
import com.fssa.reparo.model.Vehicle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class VehicleServiceTest {
    protected VehicleService  vehicleService = new VehicleService();
    @Test
    void getVehicleByIdTest(){
        try {
            Vehicle vehicle = vehicleService.getVehicleById(13);
            Assertions.assertEquals("TN03PU1002",vehicle.getVehicleNumber());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void getVehicleByUserIdTest(){
        try {
            Vehicle vehicle = vehicleService.getVehicleByUserId(35);
            User use  = vehicle.getUser();
            Assertions.assertEquals("Razak Test",use.getName());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }

}
