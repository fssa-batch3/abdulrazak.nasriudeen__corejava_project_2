package com.fssa.reparo.service;
import com.fssa.reparo.exception.ServiceException;
import com.fssa.reparo.model.Booking;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookingServiceTest {


    protected  BookingServices bookService =  new BookingServices();


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

            assertEquals("invalid booking Credentials", exception.getMessage());


    }



     @Test
     @Order(3)
    void updateRequestTest(){
         try {
             bookService.updateRequestStatus(true,18);
         } catch (ServiceException e) {
             fail();
             throw new RuntimeException(e);
         }
     }
    @Test
    @Order(4)
    void updateRequestTestFail(){
        ServiceException exception = assertThrows(ServiceException.class, () ->  bookService.updateRequestStatus(true,0));

        assertEquals("com.fssa.reparo.exception.ValidationException: booking not present", exception.getMessage());

    }
    @Test
    @Order(5)
    void updateAcceptStatus(){
        try {
            bookService.updateAcceptStatus(true,18,18);
        } catch (ServiceException e) {
            fail();
            throw new RuntimeException(e);
        }

    }
    @Test
    @Order(6)
    void updateAcceptStatusFail(){
        ServiceException exception = assertThrows(ServiceException.class, () ->              bookService.updateAcceptStatus(true,18,10));

        assertEquals("com.fssa.reparo.exception.ValidationException: booking not present", exception.getMessage());

    }
    @Test
    @Order(7)
    void getBookingByIdTest(){
        try {
          Booking book =  bookService.getBookingById(18);
          assertEquals(13,book.getVehicleId());
        } catch (ServiceException e) {
           fail();
        }

    }
    @Test
    @Order(8)
    void getBookingByIdFail(){

        ServiceException exception = assertThrows(ServiceException.class, () ->    bookService.getBookingById(10));

        assertEquals("com.fssa.reparo.exception.ValidationException: booking not present", exception.getMessage());

    }
    @Test
    @Order(9)
    void getAllBookingsTest(){
        try {
            List<Booking> bookings = bookService.getAllBookings();
            assertFalse(bookings.isEmpty());
        } catch (ServiceException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    @Test
    @Order(10)
    void findWorkshopByAreaTest(){
        try {
            Assertions.assertFalse(bookService.findWorkshopByArea("chennai").isEmpty());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

    }
    @Test
    @Order(11)
    void findWorkshopByAreaTestFail(){

        ServiceException exception = assertThrows(ServiceException.class, () ->    bookService.findWorkshopByArea("chen"));

        assertEquals("com.fssa.reparo.exception.ValidationException: No Available bookings", exception.getMessage());


    }


}
