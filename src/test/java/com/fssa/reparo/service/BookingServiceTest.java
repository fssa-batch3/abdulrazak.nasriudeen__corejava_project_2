package com.fssa.reparo.service;
import com.fssa.reparo.dto.booking.BookingRequestDto;
import com.fssa.reparo.dto.booking.BookingResponseExclAcceptDto;
import com.fssa.reparo.dto.booking.BookingResponseInclAcceptDto;
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
        BookingRequestDto booking = new BookingRequestDto();
        booking.setVehicleId(13);
        booking.setProblem("Engine malFacture");
        booking.setBookingAddress("123!22wdjwuh2ednwdqwld2091L:}");
        booking.setBookingCity("chennai1223");
        booking.setBookingState("Tamil Nadu");


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
            bookService.updateAcceptStatus(true,18,22);
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
    @Test
    @Order(12)
    void getUnAcceptedLiveBookingTest(){

        try {
            BookingResponseExclAcceptDto book = null;
            book = bookService.getUnAcceptedLiveBookingById(22);
            assertEquals("Razak Test",book.getVehicleInfo().getUserInfo().getName());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }


    }
    @Test
    @Order(13)
    void getAcceptedLiveBookingTest(){
        BookingResponseInclAcceptDto book = null;
        try {
            book = bookService.getAcceptedLiveBookingById(22);
            assertEquals("Auto mobiles",book.getWorkshopInfo().getWorkshopName());

        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }


    }
    @Test
    @Order(14)
    void getAllAcceptedLiveBookingTest()  {

        try {
            List<BookingResponseInclAcceptDto> bookings = bookService.getAllAcceptedBooking();
            assertEquals("Auto mobiles",bookings.get(0).getWorkshopInfo().getWorkshopName());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }


    }
    @Test
    @Order(15)
    void getAllUnAcceptedLiveBookingTest()  {

        try {
            List<BookingResponseExclAcceptDto> bookings = bookService.getAllUnAcceptedBooking();
            assertEquals("Razak Test",bookings.get(0).getVehicleInfo().getUserInfo().getName());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }


    }


}
