package com.fssa.reparo.dto.booking;
import com.fssa.reparo.dto.vehicle.VehicleResponseDto;

public class BookingResponseExclAcceptDto extends BookingDto {
    private BookingRequestDto bookingInfo;
    private VehicleResponseDto vehicleInfo;


    public BookingResponseExclAcceptDto(int bookingId ,BookingRequestDto bookingInfo, VehicleResponseDto vehicleInfo) {
        super(bookingId);
        this.bookingInfo = bookingInfo;
        this.vehicleInfo = vehicleInfo;
    }

    public BookingRequestDto getBookingInfo() {
        return bookingInfo;
    }

    public void setBookingInfo(BookingRequestDto bookingInfo) {
        this.bookingInfo = bookingInfo;
    }

    public VehicleResponseDto getVehicleInfo() {
        return vehicleInfo;
    }

    public void setVehicleInfo(VehicleResponseDto vehicleInfo) {
        this.vehicleInfo = vehicleInfo;
    }

}

