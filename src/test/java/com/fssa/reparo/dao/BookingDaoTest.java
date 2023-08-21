package com.fssa.reparo.dao;
import com.fssa.reparo.exception.DAOException;
import com.fssa.reparo.model.Booking;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
 class BookingDaoTest {
    @Test
     void insertBookingTest(){
        Booking book  = new Booking();

        book.setVehicleId(1);
        book.setAddress("123123");
        book.setCity("chennai");
        book.setState("TamilNadu");
        book.setProblem("punchre");


        try {
            BookingDao bookDao = new BookingDao();
            Assertions.assertTrue(bookDao.insertBooking(book));
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void updateRequestStsTest(){
        try {
            BookingDao bookDao = new BookingDao();
            Assertions.assertTrue(bookDao.updateRequestSts(1,true));
            Booking book1 = bookDao.getBookingsByVehicleId(1);
            Assertions.assertTrue(book1.isRequestStatus());
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void updateAcceptStatus(){
        try {
            BookingDao bookDao = new BookingDao();
            Assertions.assertTrue(bookDao.updateAcceptSts(1,2,true));
            Booking book = bookDao.getBookingsByVehicleId(1);
            Assertions.assertTrue(book.isAcceptStatus());
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }
    }
    @AfterAll
   static void removeBooking(){
        try {
            BookingDao bookDao = new BookingDao();
            Assertions.assertTrue(bookDao.removeBooking(1));
        } catch (DAOException e) {
            throw new RuntimeException(e);}
    }



}
