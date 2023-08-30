package com.fssa.reparo.validation;

import com.fssa.reparo.exception.ServiceException;
import com.fssa.reparo.exception.ValidationException;
import com.fssa.reparo.model.Booking;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookingValidationTest {
    protected BookingValidation bookingValidate =  new BookingValidation();
    @Test
    void bookingCredentialsTest(){
        Booking booking =  new Booking();
         booking.setVehicleId(13);
         booking.setState("Tamil nadu");
         booking.setCity("chennai");
         booking.setAddress("123 arul nagar");
         booking.setProblem("punchre");
         booking.setRequestStatus(true);
        try {
            bookingValidate.validBooking(booking);
        } catch (ValidationException e) {
            fail();
        }
    }
    @Test
    void bookingCredentialsTestFail() {
        Booking booking = new Booking();
        booking.setVehicleId(13);
        booking.setState("Tamil nadu");
        booking.setCity("chen");
        booking.setAddress("1hshsnsoix!keekc][]");
        booking.setProblem("punchre");
        booking.setRequestStatus(true);
        ValidationException exception = assertThrows(ValidationException.class, () -> bookingValidate.validBooking(booking));

        assertEquals("invalid booking Credentials", exception.getMessage());



    }
        @Test
    void isBookingIdTest(){
        try {
            bookingValidate.isBookingId(22);
        } catch (ValidationException e) {
            fail();
         }
    }
    @Test
    void isNotBookingIdTest(){
        ValidationException exception = assertThrows(ValidationException.class, () -> bookingValidate.isBookingId(0));

        assertEquals("booking not present", exception.getMessage());

    }



}
