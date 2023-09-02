package com.fssa.reparo.service;
import com.fssa.reparo.datamapper.VehicleMapper;
import com.fssa.reparo.dto.vehicle.VehicleRequestDto;
import com.fssa.reparo.dto.vehicle.VehicleResponseDto;
import com.fssa.reparo.exception.ServiceException;
import com.fssa.reparo.dao.VehicleDAO;
import com.fssa.reparo.exception.DAOException;
import com.fssa.reparo.exception.InvalidEntryException;
import com.fssa.reparo.exception.ValidationException;
import com.fssa.reparo.model.Vehicle;
import com.fssa.reparo.validation.UserValidation;

import java.util.ArrayList;
import java.util.List;

public  class VehicleService  {
    public boolean addVehicle(VehicleRequestDto request) throws ServiceException {
        UserValidation validate =  new UserValidation();
        VehicleMapper map =  new VehicleMapper();
        Vehicle  vehicle = map.mapVehicleRequestDtoToVehicle(request);
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
    public VehicleResponseDto getVehicleById(int id) throws  ServiceException{
        VehicleDAO vehicleDao = new VehicleDAO();
        VehicleMapper map = new VehicleMapper();
        try {

            return map.mapVehicleToResponseDto(vehicleDao.findVehicleById(id));
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
    public List<VehicleResponseDto> getAllVehicles() throws  ServiceException{
        VehicleDAO vehicleDao = new VehicleDAO();
        VehicleMapper map =  new VehicleMapper();
        List<VehicleResponseDto> vehicles =  new ArrayList<>();

        try {
            for(Vehicle vehicle:vehicleDao.getAllVehicles()){
                VehicleResponseDto response = map.mapVehicleToResponseDto(vehicle);
                vehicles.add(response);
            }
            return vehicles;
        } catch (DAOException e) {
            throw new ServiceException(e);
        }


    }

}
