package com.fssa.reparo.validation;
import com.fssa.reparo.exception.InvalidEntryException;
import com.fssa.reparo.exception.ValidationException;
import com.fssa.reparo.model.Booking;

public class BookingValidation {

    public boolean validBooking(Booking book) throws ValidationException {
        Validations  validate =  new Validations();
        try {
            return validate.stringValidation(book.getCity()) && validVehicleId(book.getVehicleId()) && validate.addressValidation(book.getAddress())&&validate.stringValidation(book.getState())&&validate.stringValidation(book.getProblem());
        } catch (ValidationException | InvalidEntryException e) {
            throw new ValidationException(e);
        }

    }
    public boolean validVehicleId(int id) throws ValidationException {
        UserValidation userValidate =  new UserValidation();
        return userValidate.validUserId(id);
    }

}
