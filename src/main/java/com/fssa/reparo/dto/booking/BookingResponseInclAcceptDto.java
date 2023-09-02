package com.fssa.reparo.dto.booking;
import com.fssa.reparo.dto.vehicle.VehicleResponseDto;
import com.fssa.reparo.dto.workshop.WorkShopResponseDto;


public class BookingResponseInclAcceptDto extends BookingDto {
    private BookingRequestDto bookingInfo;
    private VehicleResponseDto vehicleInfo;
    private WorkShopResponseDto workshopInfo;



    public BookingResponseInclAcceptDto(int bookingId ,BookingRequestDto bookingInfo, VehicleResponseDto vehicleInfo, WorkShopResponseDto workshopInfo) {
        super(bookingId);
        this.bookingInfo = bookingInfo;
        this.vehicleInfo = vehicleInfo;
        this.workshopInfo =  workshopInfo;
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

    public WorkShopResponseDto getWorkshopInfo() {
        return workshopInfo;
    }

    public void setWorkshopInfo(WorkShopResponseDto workshopInfo) {
        this.workshopInfo = workshopInfo;
    }
}
