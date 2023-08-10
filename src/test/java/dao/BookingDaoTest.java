package dao;

import exception.DaoException;
import model.Booking;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class BookingDaoTest {
   @BeforeAll
    static void insertBookingTest(){
        Booking book  = new Booking();
        book.setVehicleId(1);
        book.setAddress("123123");
        book.setCity("chennai");
        book.setState("TamilNadu");
        book.setProblem("punChre");
        try {
            Assertions.assertTrue(BookingDao.insertBooking(book));
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void updateRequestStsTest(){
        try {
            Assertions.assertTrue(BookingDao.updateRequestSts(1,true));
            Booking book1 = BookingDao.getBookingsByVehicleId(1);
            Assertions.assertTrue(book1.isRequestStatus());
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void updateAcceptStatus(){
        try {
            Assertions.assertTrue(BookingDao.updateAcceptSts(1,true));
            Booking book = BookingDao.getBookingsByVehicleId(1);
            Assertions.assertTrue(book.isAcceptStatus());
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getWorkshopByAreaTest(){
        try {
            ArrayList<Integer> arr = BookingDao.findWorkshopByArea("chennai");
            Assertions.assertEquals(14,arr.get(0));
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }

    }


    @AfterAll
   static void removeBooking(){
        try {
            Assertions.assertTrue(BookingDao.removeBooking(1));
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
    }



}
