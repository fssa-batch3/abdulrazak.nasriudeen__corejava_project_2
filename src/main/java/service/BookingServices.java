package service;
import dao.BookingDao;
import exception.DAOException;
import exception.InvalidEntryException;
import exception.ServiceException;
import model.Booking;
import validation.BookingValidation;

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
