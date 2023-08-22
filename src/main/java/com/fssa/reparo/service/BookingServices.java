package com.fssa.reparo.service;
import com.fssa.reparo.dao.BookingDao;
import com.fssa.reparo.exception.DAOException;
import com.fssa.reparo.exception.ServiceException;
import com.fssa.reparo.exception.ValidationException;
import com.fssa.reparo.model.Booking;
import com.fssa.reparo.validation.BookingValidation;
import com.fssa.reparo.validation.WorkShopValidation;

public class BookingServices {
    protected BookingDao bookingDao ;
    protected  BookingValidation bookingValidation;
    protected WorkShopValidation workshopValidate ;


    public  boolean createBooking(Booking book) throws  ServiceException{

        try {
            if(bookingValidation.validBooking(book)){
               return bookingDao.insertBooking(book);
            }
            return false;
        } catch (ValidationException | DAOException e) {
            throw new ServiceException(e);
        }
    }
    public boolean updateRequestStatus(boolean status , int id) throws ServiceException{

        try {
            if(bookingValidation.isBookingId(id)) return bookingDao.updateRequestSts(id,status);
            return  false;
        } catch (DAOException | ValidationException e) {
            throw new ServiceException(e);
        }
    }
    public boolean updateAcceptStatus(boolean status,int workShopId , int bookingId) throws ServiceException{
            try {

                if(bookingValidation.isBookingId(bookingId)&&workshopValidate.isWorkshopId(workShopId)) return bookingDao.updateAcceptSts(bookingId,workShopId,status);
                return false;
            } catch (DAOException |ValidationException e) {
                throw new ServiceException(e);
            }
    }

}
