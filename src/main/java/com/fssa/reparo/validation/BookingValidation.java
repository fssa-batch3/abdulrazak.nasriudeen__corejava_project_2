package com.fssa.reparo.validation;
import com.fssa.reparo.dao.BookingDao;
import com.fssa.reparo.exception.DAOException;
import com.fssa.reparo.exception.InvalidEntryException;
import com.fssa.reparo.exception.ValidationException;
import com.fssa.reparo.model.Booking;
public class BookingValidation {

    public boolean validBooking(Booking book) throws ValidationException {
        Validations  validate =  new Validations();
        UserValidation userValidation =  new UserValidation();
        try {
            return validate.stringValidation(book.getCity()) && userValidation.validVehicleId(book.getVehicleId()) && validate.addressValidation(book.getAddress())&&validate.stringValidation(book.getState())&&validate.stringValidation(book.getProblem());
        } catch (ValidationException | InvalidEntryException e) {
            throw new ValidationException(e);
        }

    }
    public boolean isBookingId(int id) throws ValidationException{
        BookingDao dao = new BookingDao();
        try {
            Booking book = dao.getBookingById(id);
            return book.getBookingId()!=0;
        } catch (DAOException e) {
            throw new ValidationException(e);
        }

    }


}
