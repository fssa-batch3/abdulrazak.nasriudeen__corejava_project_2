package com.fssa.reparo.service;
import com.fssa.reparo.exception.ServiceException;
import com.fssa.reparo.model.Booking;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BookingServiceTest {
    protected  BookingServices bookService =  new BookingServices();

//    @Test
//    @Order(1)
//    void createBookingTest(){
//        Booking booking = new Booking();
//        booking.setVehicleId(13);
//        booking.setProblem("Engine malFacture");
//        booking.setAddress("123 cross street");
//        booking.setCity("chennai");
//        booking.setState("Tamil Nadu");
//        booking.setRequestStatus(true);
//        booking.setLive(true);
//        try {
//           Assertions.assertTrue(bookService.createBooking(booking)) ;
//        } catch (ServiceException e) {
//            throw new RuntimeException(e);
//        }
//    }
    @Test
    @Order(2)
    void createBookingFail(){
        Booking booking = new Booking();
        booking.setVehicleId(13);
        booking.setProblem("Engine malFacture");
        booking.setAddress("123!22wdjwuh2ednwdqwld2091L:}");
        booking.setCity("chennai1223");
        booking.setState("Tamil Nadu");
        booking.setRequestStatus(true);
        booking.setLive(true);

            ServiceException exception = assertThrows(ServiceException.class, () -> bookService.createBooking(booking));

            assertEquals("com.fssa.reparo.exception.ValidationException: user not present", exception.getMessage());


    }



     @Test
    void updateRequestTest(){
         try {
             Assertions.assertTrue(bookService.updateRequestStatus(true,18));
         } catch (ServiceException e) {
             throw new RuntimeException(e);
         }
     }
    @Test
    void updateRequestTestFail(){
        try {
            Assertions.assertFalse(bookService.updateRequestStatus(true,0));
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }
}
