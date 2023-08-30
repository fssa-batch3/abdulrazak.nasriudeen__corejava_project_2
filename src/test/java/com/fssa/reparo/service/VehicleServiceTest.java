package com.fssa.reparo.service;
import com.fssa.reparo.exception.ServiceException;
import com.fssa.reparo.model.User;
import com.fssa.reparo.model.Vehicle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.List;
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
    @Test
    void getAllVehicles(){
        try {
            List<Vehicle> vehicles  = vehicleService.getAllVehicles();
            Assertions.assertFalse(vehicles.isEmpty());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }


    }
    @Test
    void getVehicleByIdTestFail(){
        try {
            Vehicle vehicle = vehicleService.getVehicleById(13);
            Assertions.assertNotEquals("TN03PU1003",vehicle.getVehicleNumber());
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
            List<Vehicle> vehicles  = vehicleService.getAllVehicles();
            Assertions.assertNotEquals(0, vehicles.size());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }


    }
}
