package com.fssa.reparo.service;
import com.fssa.reparo.dto.vehicle.VehicleResponseDto;
import com.fssa.reparo.exception.ServiceException;
import com.fssa.reparo.model.User;
import com.fssa.reparo.model.Vehicle;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;

class VehicleServiceTest {
    protected VehicleService  vehicleService = new VehicleService();

//    @BeforeAll
//   static void createvehicleTest(){
//        Vehicle vehicle =  new Vehicle();
//        vehicle.setUserId(35);
//        vehicle.setVehicleCompany("hero");
//        vehicle.setVehicleModel("passion");
//        vehicle.setVehicleType(2);
//        vehicle.setVehicleNumber("TN03PU1005");
//        vehicle.setVehicleYear(2020);
//
//        try {
//            VehicleService vehicleService = new VehicleService();
//            vehicleService.addVehicle(vehicle);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//
//
//
//
//    }




    @Test
    void getVehicleByIdTest(){
        try {
            VehicleResponseDto vehicle = vehicleService.getVehicleById(13);
            Assertions.assertEquals("TN03PU1002",vehicle.getVehicleInfo().getVehicleNumber());
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
    @Test
    void getAllVehicles(){
        try {
            Assertions.assertFalse(vehicleService.getAllVehicles().isEmpty());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }


    }
    @Test
    void getVehicleByIdTestFail(){
        try {
            VehicleResponseDto vehicle = vehicleService.getVehicleById(13);
            Assertions.assertNotEquals("TN03PU1003",vehicle.getVehicleInfo().getVehicleNumber());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void getVehicleByUserIdTestFail(){
        try {
            Vehicle vehicle = vehicleService.getVehicleByUserId(35);
            User use  = vehicle.getUser();
            Assertions.assertNotEquals("Razak",use.getName());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void getAllVehiclesFail(){
        try {
            Assertions.assertNotEquals(0, vehicleService.getAllVehicles().size());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }


    }
}
