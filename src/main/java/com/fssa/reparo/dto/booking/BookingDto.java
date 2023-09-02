package com.fssa.reparo.dto.booking;

public class BookingDto {
    private  int bookingId;

    public BookingDto(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }
}
