package com.fssa.reparo.service;
import com.fssa.reparo.dao.BookingDao;
import com.fssa.reparo.exception.DAOException;
import com.fssa.reparo.exception.ServiceException;
import com.fssa.reparo.exception.ValidationException;
import com.fssa.reparo.model.Booking;
import com.fssa.reparo.validation.BookingValidation;
import com.fssa.reparo.validation.WorkShopValidation;
import java.util.List;
public class BookingServices {
    protected BookingDao bookingDao =  new BookingDao();
    protected  BookingValidation bookingValidation =  new BookingValidation();
    protected WorkShopValidation workshopValidate = new WorkShopValidation();


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
    public Booking getBookingById(int id)throws ServiceException{
        try {
            Booking booking = new Booking();
            if(bookingValidation.isBookingId(id)) booking =  bookingDao.getBookingById(id);
            return booking;
        } catch (ValidationException |DAOException e) {
            throw new ServiceException(e);
        }

    }
    public List<Booking> getAllBookings() throws  ServiceException{
        try {
            return bookingDao.getAllBookings();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

}
