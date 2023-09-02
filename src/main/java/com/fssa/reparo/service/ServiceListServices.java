package com.fssa.reparo.service;

import com.fssa.reparo.dao.ServiceDao;
import com.fssa.reparo.exception.DAOException;
import com.fssa.reparo.exception.InvalidEntryException;
import com.fssa.reparo.exception.ServiceException;
import com.fssa.reparo.exception.ValidationException;
import com.fssa.reparo.model.Services;
import com.fssa.reparo.validation.BookingValidation;

public class ServiceListServices {
    private ServiceDao serviceDao = new ServiceDao();

    public boolean createServiceList(int bookingId) throws ServiceException {
        BookingValidation validation =  new BookingValidation();
        try {
            validation.isBookingId(bookingId);
           return serviceDao.createServiceList(bookingId);

        } catch (ValidationException | DAOException e) {
            throw new ServiceException(e);
        }


    }
    public Services getServiceByBookingId(int id) throws ServiceException {
        BookingValidation validation =  new BookingValidation();
        try {
            validation.isBookingId(id);
            return serviceDao.getServiceListByBookingId(id);

        } catch (ValidationException | DAOException e) {
            throw new ServiceException(e);
        }

    }
    public Services getServiceById(int id) throws ServiceException {
        BookingValidation validation =  new BookingValidation();
        try {
            validation.isServiceId(id);
            return serviceDao.getServiceListById(id);

        } catch (ValidationException | DAOException e) {
            throw new ServiceException(e);
        }

    }
    public boolean updateServiceAmount(int serviceListId , int amount) throws ServiceException {
        BookingValidation validation =  new BookingValidation();
        try {
            validation.isServiceId(serviceListId);
            validation.priceValidation(amount);
            return serviceDao.updateServiceAmount(serviceListId,amount);
        } catch (ValidationException | DAOException | InvalidEntryException e) {
            throw new ServiceException(e);
        }
    }
    public boolean updateCancelService(int serviceListId , boolean cancel , String reason ) throws ServiceException {
        BookingValidation validation =  new BookingValidation();
        try {
            validation.isServiceId(serviceListId);

            return serviceDao.updateCancelStatus(serviceListId,cancel,reason);
        } catch (ValidationException | DAOException  e) {
            throw new ServiceException(e);
        }
    }
    public boolean updateAcceptService(int serviceListId , boolean accept  ) throws ServiceException {
        BookingValidation validation =  new BookingValidation();
        try {
            validation.isServiceId(serviceListId);
            return serviceDao.updateAcceptStatus(serviceListId,accept);
        } catch (ValidationException | DAOException  e) {
            throw new ServiceException(e);
        }
    }
}
