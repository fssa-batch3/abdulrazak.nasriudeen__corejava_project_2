package service;

import dao.BookingsDao;
import exception.DaoException;
import model.Bookings;
import validation.InvalidEntryException;
import validation.UserValidation;

public class BookingServices {
    public void createBooking(Bookings book){
        UserValidation validate = new UserValidation();

        try {
            if(validate.bookingValidation(book)){

                BookingsDao.insertBooking(book);

            }
        } catch (InvalidEntryException | DaoException e) {
            throw new RuntimeException(e);
        }

    }
}
