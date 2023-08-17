package validation;

import exception.InvalidEntryException;
import model.Booking;

public class BookingValidation {

    public boolean validBooking(Booking book) throws InvalidEntryException {
        Validations  validate =  new Validations();
        return validate.stringValidation(book.getCity()) && validate.addressValidation(book.getAddress())&&validate.stringValidation(book.getState())&&validate.stringValidation(book.getProblem());

    }

}
