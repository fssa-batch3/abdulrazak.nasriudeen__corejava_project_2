package com.fssa.reparo.dto.booking;

public class BookingResponseDto extends BookingDto{
    private BookingRequestDto bookingInfo;

    public BookingResponseDto(int bookingId, BookingRequestDto bookingInfo) {
        super(bookingId);
        this.bookingInfo = bookingInfo;
    }

    public BookingRequestDto getBookingInfo() {
        return bookingInfo;
    }

    public void setBookingInfo(BookingRequestDto bookingInfo) {
        this.bookingInfo = bookingInfo;
    }



}
