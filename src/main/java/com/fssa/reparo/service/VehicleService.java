package com.fssa.reparo.service;


import com.fssa.reparo.exception.ServiceException;
import com.fssa.reparo.dao.VehicleDao;
import com.fssa.reparo.exception.DAOException;
import com.fssa.reparo.exception.InvalidEntryException;
import com.fssa.reparo.model.Vehicle;
import com.fssa.reparo.validation.UserValidation;
import java.util.ArrayList;


public  class VehicleService  {
    public boolean createVehicle(Vehicle vehicle) throws ServiceException {
        UserValidation validate =  new UserValidation();
        VehicleDao vehicleDao = new VehicleDao();
        try {
            if(validate.userValidVehicle(vehicle)){
                Vehicle  present =  vehicleDao.findVehicleByUserId(vehicle.getUser_id());
                if(!present.getVehicleNumber().equals(vehicle.getVehicleNumber())){
                  return vehicleDao.insertVehicle(vehicle);
                }
                return false;



            }
            return false;
        } catch (InvalidEntryException | DAOException e) {
            throw new ServiceException(e);
        }


    }
    public Vehicle getVehicleById(int id) throws  ServiceException{
        VehicleDao vehicleDao = new VehicleDao();
        try {
            return vehicleDao.findVehicleById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
    public Vehicle getVehicleByUserId(int id) throws  ServiceException{
        VehicleDao vehicleDao = new VehicleDao();
        try {
            return vehicleDao.findVehicleByUserId(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }


    }
    public ArrayList<Vehicle> getAllVehicles() throws  ServiceException{
        VehicleDao vehicleDao = new VehicleDao();
        try {
            return vehicleDao.getAllVehicles();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }


    }

}
