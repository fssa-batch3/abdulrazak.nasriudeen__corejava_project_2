package com.fssa.reparo.validation;
import com.fssa.reparo.dao.BookingDAO;
import com.fssa.reparo.exception.DAOException;
import com.fssa.reparo.exception.InvalidEntryException;
import com.fssa.reparo.exception.ValidationException;
import com.fssa.reparo.model.Booking;
import com.fssa.reparo.model.Services;

import java.util.List;

public class BookingValidation {


    public void validBooking(Booking book) throws ValidationException {
        Validations  validate =  new Validations();
        UserValidation userValidation =  new UserValidation();
        try {
            if(!(validate.stringValidation(book.getCity()) && userValidation.validVehicleId(book.getVehicleId()) && validate.addressValidation(book.getAddress())&&validate.stringValidation(book.getState())&&validate.stringValidation(book.getProblem()))){
                throw new ValidationException("invalid booking Credentials");
            }

        } catch (ValidationException | InvalidEntryException e) {
            throw new ValidationException(e.getMessage());
        }

    }
    public void isBookingId(int id) throws ValidationException{
        BookingDAO dao = new BookingDAO();
        try {
            Booking book = dao.getBookingById(id);
            if(book==null)throw new ValidationException("booking not present");

        } catch (DAOException e) {
            throw new ValidationException(e);
        }

    }

    public void bookingCityValidation(String city)throws ValidationException {
        BookingDAO bookingDAO =  new BookingDAO();
        Validations validate = new Validations();
        try {
            if(validate.stringValidation(city)) {
                List<Booking> bookings = bookingDAO.findBookingNearByCity(city);
                if(bookings.isEmpty()) throw  new ValidationException("No Available bookings");
            }else{
                throw new ValidationException("city name should not contain special characters or numbers ");
            }
        } catch (DAOException | InvalidEntryException e) {

            throw new ValidationException(e);
        }

    }

    public void serviceCredentialValidation(Services services) throws ValidationException {
        Validations validations =  new Validations();
        try {
           validations.priceValidation(services.getServiceAmount());
           isBookingId(services.getBookingId());
        } catch (InvalidEntryException |ValidationException e) {
            throw new ValidationException(e);
        }

    }






}
