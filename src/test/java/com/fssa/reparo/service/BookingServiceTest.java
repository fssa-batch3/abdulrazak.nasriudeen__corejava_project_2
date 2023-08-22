package com.fssa.reparo.service;
import com.fssa.reparo.model.Booking;
import org.junit.jupiter.api.Test;

class BookingServiceTest {
    protected int bookingId = 0 ;

    @Test
    void createBookingTest(){
        Booking book = new Booking();
        book.setVehicleId(2);

    }

}
