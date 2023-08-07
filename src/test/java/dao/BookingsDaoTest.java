package dao;

import exception.DaoException;
import model.Bookings;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
public class BookingsDaoTest {
    @Test
    void  insertBookingTest(){
        Bookings book = new Bookings();
        book.setCity("chennai");
        book.setAddress("3/401");
        book.setState("TamilNadu");
        book.setVehicleId(1);
        book.setCustomerId(3);
        try {
            Assertions.assertTrue(BookingsDao.insertBooking(book));
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void updateBookingStatusTest(){
        try {
            Assertions.assertTrue(BookingsDao.updateRequestSts(3));
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterAll
    static  void deleteBookingTest(){
        try {
            Assertions.assertTrue(BookingsDao.deleteBooking(3));
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
    }

}