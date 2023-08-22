package com.fssa.reparo.validation;

import com.fssa.reparo.exception.ValidationException;
import com.fssa.reparo.model.Booking;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
            Assertions.assertTrue(bookingValidate.validBooking(booking));
        } catch (ValidationException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void bookingCredentialsTestFail(){
        Booking booking =  new Booking();
        booking.setVehicleId(13);
        booking.setState("Tamil nadu");
        booking.setCity("chen");
        booking.setAddress("1hshsnsoix!keekc][]");
        booking.setProblem("punchre");
        booking.setRequestStatus(true);
        try {
            Assertions.assertFalse(bookingValidate.validBooking(booking));
        } catch (ValidationException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void isBookingIdTest(){
        try {
            Assertions.assertTrue(bookingValidate.isBookingId(18));
        } catch (ValidationException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void isNotBookingIdTest(){
        try {
            Assertions.assertFalse(bookingValidate.isBookingId(0));
        } catch (ValidationException e) {
            throw new RuntimeException(e);
        }
    }
}
