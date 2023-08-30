package com.fssa.reparo.service;
import com.fssa.reparo.dao.BookingDAO;
import com.fssa.reparo.exception.DAOException;
import com.fssa.reparo.exception.ServiceException;
import com.fssa.reparo.exception.ValidationException;
import com.fssa.reparo.model.Booking;
import com.fssa.reparo.validation.BookingValidation;
import com.fssa.reparo.validation.WorkShopValidation;
import java.util.List;
public class BookingServices {
    protected BookingDAO bookingDao =  new BookingDAO();
    protected  BookingValidation bookingValidation =  new BookingValidation();
    protected WorkShopValidation workshopValidate = new WorkShopValidation();


    public  void createBooking(Booking book) throws  ServiceException{

        try {
            bookingValidation.validBooking(book);
            bookingDao.insertBooking(book);


        } catch (ValidationException | DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }
    public boolean updateRequestStatus(boolean status , int id) throws ServiceException{

        try {
            bookingValidation.isBookingId(id) ;
        return bookingDao.updateRequestSts(id,status);

        } catch (DAOException | ValidationException e) {
            throw new ServiceException(e);
        }
    }
    public void updateAcceptStatus(boolean status,int workShopId , int bookingId) throws ServiceException{
            try {

                bookingValidation.isBookingId(bookingId);
                workshopValidate.isWorkshopId(workShopId);
                bookingDao.updateAcceptSts(bookingId,workShopId,status);

            } catch (DAOException |ValidationException e) {
                throw new ServiceException(e);
            }
    }
    public Booking getBookingById(int id)throws ServiceException{
        try {
            Booking booking;
            bookingValidation.isBookingId(id);
            booking =  bookingDao.getBookingById(id);
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
