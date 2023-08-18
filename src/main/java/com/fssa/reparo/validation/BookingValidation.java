package com.fssa.reparo.validation;

import com.fssa.reparo.exception.InvalidEntryException;
import com.fssa.reparo.model.Booking;

public class BookingValidation {

    public boolean validBooking(Booking book) throws InvalidEntryException {
        Validations  validate =  new Validations();
        return validate.stringValidation(book.getCity()) && validate.addressValidation(book.getAddress())&&validate.stringValidation(book.getState())&&validate.stringValidation(book.getProblem());

    }

}
