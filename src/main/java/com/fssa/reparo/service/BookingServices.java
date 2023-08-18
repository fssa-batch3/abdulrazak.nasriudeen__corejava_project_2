package com.fssa.reparo.service;
import com.fssa.reparo.dao.BookingDao;
import com.fssa.reparo.exception.DAOException;
import com.fssa.reparo.exception.InvalidEntryException;
import com.fssa.reparo.exception.ServiceException;
import com.fssa.reparo.model.Booking;
import com.fssa.reparo.validation.BookingValidation;

public class BookingServices {
    public  boolean createBooking(Booking book) throws  ServiceException{
        BookingValidation validate = new BookingValidation();
        BookingDao dao =  new BookingDao();
        try {
            if(validate.validBooking(book)){

               return dao.insertBooking(book);

            }
            return false;
        } catch (InvalidEntryException | DAOException e) {
            throw new ServiceException(e);
        }

    }
}
