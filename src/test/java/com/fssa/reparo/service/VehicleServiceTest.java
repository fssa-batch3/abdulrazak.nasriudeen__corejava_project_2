package com.fssa.reparo.service;
import com.fssa.reparo.dao.VehicleDao;
import com.fssa.reparo.dto.user.UserResponseDto;
import com.fssa.reparo.dto.vehicle.VehicleRequestDto;
import com.fssa.reparo.dto.vehicle.VehicleResponseDto;
import com.fssa.reparo.exception.DAOException;
import com.fssa.reparo.exception.ServiceException;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

class VehicleServiceTest {
    protected VehicleService  vehicleService = new VehicleService();
    @Test
    @Order(1)
    void createVehicle(){
        VehicleRequestDto request =  new VehicleRequestDto();
        request.setCompany("hero");
        request.setModel("passion");
        request.setVehicleNumber("TN03PU1005");
        request.setType(2);
        request.setUserId(35);
        request.setYear(2020);
        try {
            VehicleService vehicleService = new VehicleService();
          Assertions.assertTrue(vehicleService.addVehicle(request));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    @Test
    @Order(2)
    void getVehicleByIdTest(){
        try {
            VehicleResponseDto vehicle = vehicleService.getVehicleById(13);
            Assertions.assertEquals("TN03PU1002",vehicle.getVehicleInfo().getVehicleNumber());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    @Order(3)
    void getVehicleByUserIdTest(){
        try {
            VehicleResponseDto vehicle = vehicleService.getVehicleByUserId(35);
            UserResponseDto use  = vehicle.getUserInfo();
            Assertions.assertEquals("Razak Test",use.getName());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    @Order(4)
    void getAllVehicles(){
        try {
            Assertions.assertFalse(vehicleService.getAllVehicles().isEmpty());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }


    }
    @Test
    @Order(5)
    void getVehicleByIdTestFail(){
        ServiceException exception = assertThrows(ServiceException.class, () -> vehicleService.getVehicleById(12));
        String[] arr = exception.getMessage().split(":");
        assertEquals(" vehicle not present", arr[arr.length - 1]);
    }
    @Test
    @Order(6)
    void getVehicleByUserIdTestFail(){
        ServiceException exception = assertThrows(ServiceException.class, () -> vehicleService.getVehicleByUserId(12));
        String[] arr = exception.getMessage().split(":");
        assertEquals(" vehicle not present", arr[arr.length - 1]);
    }
    @Test
    @Order(7)
    void deleteVehicleByVehicleNumber(){
        VehicleDao vehicle = new VehicleDao();
        try{
            Assertions.assertTrue(vehicle.removeVehicleByVehicleNumber("TN03PU1005"));
        }catch (DAOException e){ e.printStackTrace();}
    }

}
