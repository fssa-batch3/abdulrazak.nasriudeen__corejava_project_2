package com.fssa.reparo.service;
import com.fssa.reparo.dao.BookingDao;
import com.fssa.reparo.exception.DAOException;
import com.fssa.reparo.exception.InvalidEntryException;
import com.fssa.reparo.exception.ServiceException;
import com.fssa.reparo.model.Booking;
import com.fssa.reparo.validation.BookingValidation;

public class BookingServices {
    protected BookingDao bookingDao ;
    protected  BookingValidation validate;
    public  boolean createBooking(Booking book) throws  ServiceException{

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
            if(validate.validId(id)) return bookingDao.updateRequestSts(id,status);
            return  false;
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
    public boolean updateAcceptStatus(boolean status,int workShopId , int bookingId) throws ServiceException{
            try {

                return bookingDao.updateAcceptSts(bookingId,workShopId,status);
            } catch (DAOException e) {
                throw new ServiceException(e);
            }
    }

}
