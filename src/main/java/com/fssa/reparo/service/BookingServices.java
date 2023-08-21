package com.fssa.reparo.service;
import com.fssa.reparo.dao.BookingDao;
import com.fssa.reparo.exception.DAOException;
import com.fssa.reparo.exception.InvalidEntryException;
import com.fssa.reparo.exception.ServiceException;
import com.fssa.reparo.model.Booking;
import com.fssa.reparo.validation.BookingValidation;

public class BookingServices {
    protected BookingDao bookingDao ;
    public  boolean createBooking(Booking book) throws  ServiceException{
        BookingValidation validate = new BookingValidation();
        try {
            if(validate.validBooking(book)){
               return bookingDao.insertBooking(book);
            }
            return false;
        } catch (InvalidEntryException | DAOException e) {
            throw new ServiceException(e);
        }
    }
    public boolean updateRequestStatus(boolean status , int id) throws ServiceException{
        try {
            return bookingDao.updateRequestSts(id,status);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
   
}
