package com.fssa.reparo.service;

import com.fssa.reparo.dao.ServiceDao;
import com.fssa.reparo.exception.DAOException;
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
}
