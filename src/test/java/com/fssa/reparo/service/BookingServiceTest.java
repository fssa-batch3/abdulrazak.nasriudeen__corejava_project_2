package com.fssa.reparo.service;
import com.fssa.reparo.dao.BookingDao;
import com.fssa.reparo.dto.booking.BookingRequestDto;
import com.fssa.reparo.dto.booking.BookingResponseDto;
import com.fssa.reparo.dto.booking.BookingResponseExclAcceptDto;
import com.fssa.reparo.dto.booking.BookingResponseInclAcceptDto;
import com.fssa.reparo.exception.DAOException;
import com.fssa.reparo.exception.ServiceException;
import com.fssa.reparo.model.Booking;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)


class BookingServiceTest {


    protected  BookingServices bookService =  new BookingServices();
@Test
@Order(1)
void createBookingTest(){
    BookingRequestDto booking = new BookingRequestDto();
    booking.setVehicleId(67);
    booking.setProblem("Engine malFacture");
    booking.setBookingAddress("2nd cross street");
    booking.setBookingCity("chennai");
    booking.setBookingState("Tamil Nadu");
    try {
      Assertions.assertNotEquals(0,bookService.createBooking(booking));
    } catch (ServiceException e) {
        throw new RuntimeException(e);
    }

}



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

            assertEquals("Input should only be in Alphabets ", exception.getMessage());


    }



     @Test
     @Order(3)
    void updateRequestTest(){
         try {
             bookService.updateRequestStatus(false,18);
         } catch (ServiceException e) {
             fail();
             throw new RuntimeException(e);
         }
     }

    @Test
    @Order(4)
    void updateAcceptStatus(){
        try {
            bookService.updateAcceptStatus(false,18,18
            );
        } catch (ServiceException e) {
            fail();
            throw new RuntimeException(e);
        }

    }
    @Test
    @Order(5)
    void updateAcceptStatusFail(){
        ServiceException exception = assertThrows(ServiceException.class, () ->              bookService.updateAcceptStatus(true,18,10));

        assertEquals("com.fssa.reparo.exception.ValidationException: booking not present", exception.getMessage());

    }
    @Test
    @Order(6)
    void getBookingByIdTest(){
        try {
            BookingResponseDto book =  bookService.getBookingById(18);
          assertEquals(13,book.getBookingInfo().getVehicleId());
        } catch (ServiceException e) {
           fail();
        }

    }
    @Test
    @Order(7)
    void getBookingByVehicleIdTest(){
        try {
            BookingResponseDto book =  bookService.getBookingByVehicleId(13);
            assertEquals(18,book.getBookingId());
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
            Assertions.assertFalse(bookService.findBookingByArea("chennai").isEmpty());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

    }
    @Test
    @Order(11)
    void findWorkshopByAreaTestFail(){

        ServiceException exception = assertThrows(ServiceException.class, () ->    bookService.findBookingByArea("chen"));

        assertEquals("com.fssa.reparo.exception.ValidationException: No Available bookings", exception.getMessage());


    }
    @Test
    @Order(13)
    void getUnAcceptedLiveBookingTest(){
        BookingResponseExclAcceptDto book = null;
        try {
            book = bookService.getUnAcceptedLiveBookingById(60);
            System.out.println(book.getVehicleInfo().getUserInfo().getName());
            assertEquals("Abdul Razak",book.getVehicleInfo().getUserInfo().getName());

        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }


    }
    @Test
    @Order(13)
    void getAcceptedLiveBookingTest(){
        BookingResponseInclAcceptDto book = null;
        try {
            book = bookService.getAcceptedLiveBookingById(59);
            System.out.println(book.getVehicleInfo().getUserInfo().getName());
            assertEquals("Al sa Workshop",book.getWorkshopInfo().getWorkshopName());

        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }


    }
    @Test
    @Order(14)
    void getAllAcceptedLiveBookingTest()  {

        try {
            List<BookingResponseInclAcceptDto> bookings = bookService.getAllAcceptedBooking();
            assertEquals("Al sa Workshop",bookings.get(0).getWorkshopInfo().getWorkshopName());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }


    }
    @Test
    @Order(15)
    void getAllUnAcceptedLiveBookingTest()  {

        try {
            List<BookingResponseExclAcceptDto> bookings = bookService.getAllUnAcceptedBooking();
            assertEquals("Abdul Razak",bookings.get(0).getVehicleInfo().getUserInfo().getName());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }


    }
    @Test
    @Order(16)
    void removeBookingTest(){
        BookingDao dao =  new BookingDao();
        try {
          Assertions.assertTrue(dao.removeBookingByVehicleId(67));
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }
    }


}
