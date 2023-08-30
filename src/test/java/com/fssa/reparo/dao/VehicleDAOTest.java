package com.fssa.reparo.dao;

import com.fssa.reparo.exception.DAOException;
import com.fssa.reparo.model.Vehicle;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

 class VehicleDAOTest {

    @BeforeAll
    static void insertVehicleTest(){
        Vehicle veh = new Vehicle();
        veh.setVehicleCompany("Honda");
        veh.setVehicleModel("spl");
        veh.setVehicleType(3);
        veh.setVehicleNumber("TN03PU1002");
        veh.setUserId(3);
        veh.setVehicleYear(2004);
        try{
            VehicleDAO vehicle = new VehicleDAO();
           Assertions.assertTrue(vehicle.insertVehicle(veh));
        }catch (DAOException e){
            e.printStackTrace();
        }

    }

    @Test
    void insertUserSuccess(){
        try {
            VehicleDAO vehicle = new VehicleDAO();
            Vehicle veh = vehicle.findVehicleByUserId(3);
            Assertions.assertEquals(2004,veh.getVehicleYear());
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getAllVehiclesTest(){
        try {
            VehicleDAO vehicle = new VehicleDAO();
            List<Vehicle> arr = vehicle.getAllVehicles();
            Assertions.assertNotEquals(0,arr.size());
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }
    }


    @AfterAll
   static void removeVehicleTest()
    {
        VehicleDAO vehicle = new VehicleDAO();
        try{
            Assertions.assertTrue(vehicle.removeVehicle(3));
        }catch (DAOException e){ e.printStackTrace();}
    }







}
