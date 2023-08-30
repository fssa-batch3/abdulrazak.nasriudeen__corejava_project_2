package com.fssa.reparo.service;
import com.fssa.reparo.exception.ServiceException;
import com.fssa.reparo.dao.VehicleDAO;
import com.fssa.reparo.exception.DAOException;
import com.fssa.reparo.exception.InvalidEntryException;
import com.fssa.reparo.exception.ValidationException;
import com.fssa.reparo.model.Vehicle;
import com.fssa.reparo.validation.UserValidation;

import java.util.List;

public  class VehicleService  {
    public boolean addVehicle(Vehicle vehicle) throws ServiceException {
        UserValidation validate =  new UserValidation();

        VehicleDAO vehicleDao = new VehicleDAO();
        try {
            if(validate.userValidVehicle(vehicle) && validate.validUserId(vehicle.getUserId())){

                  return vehicleDao.insertVehicle(vehicle);
                }
                return false;
            }
        catch (InvalidEntryException | DAOException | ValidationException e) {
            throw new ServiceException(e);
        }


    }
    public Vehicle getVehicleById(int id) throws  ServiceException{
        VehicleDAO vehicleDao = new VehicleDAO();
        try {
            return vehicleDao.findVehicleById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
    public Vehicle getVehicleByUserId(int id) throws  ServiceException{
        VehicleDAO vehicleDao = new VehicleDAO();
        try {

            return vehicleDao.findVehicleByUserId(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }


    }
    public List<Vehicle> getAllVehicles() throws  ServiceException{
        VehicleDAO vehicleDao = new VehicleDAO();
        try {
            return vehicleDao.getAllVehicles();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }


    }

}
