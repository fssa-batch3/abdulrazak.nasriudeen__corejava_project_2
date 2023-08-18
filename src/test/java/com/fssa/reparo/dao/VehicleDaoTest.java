package com.fssa.reparo.dao;

import com.fssa.reparo.dao.VehicleDao;
import com.fssa.reparo.exception.DAOException;
import com.fssa.reparo.model.Vehicle;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class VehicleDaoTest {

    @BeforeAll
    static void insertVehicleTest(){
        Vehicle veh = new Vehicle();
        veh.setVehicleCompany("Honda");
        veh.setVehicleModel("spl");
        veh.setVehicleType(3);
        veh.setVehicleNumber("TN03PU1002");
        veh.setUser_id(3);
        veh.setVehicleYear(2004);
        try{
            VehicleDao vehicle = new VehicleDao();
           Assertions.assertTrue(vehicle.insertVehicle(veh));
        }catch (DAOException e){
            e.printStackTrace();
        }

    }

    @Test
    void insertUserSuccess(){
        try {
            VehicleDao vehicle = new VehicleDao();
            Vehicle veh = vehicle.findVehicleByUserId(3);
            Assertions.assertEquals(2004,veh.getVehicleYear());
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getAllVehiclesTest(){
        try {
            VehicleDao vehicle = new VehicleDao();
            ArrayList<Vehicle> arr = vehicle.getAllVehicles();
            Assertions.assertTrue(arr.size()!=0);
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }
    }


    @AfterAll
   static void removeVehicleTest()
    {
        VehicleDao vehicle = new VehicleDao();
        try{
            Assertions.assertTrue(vehicle.removeVehicle(3));
        }catch (DAOException e){ e.printStackTrace();}
    }







}
