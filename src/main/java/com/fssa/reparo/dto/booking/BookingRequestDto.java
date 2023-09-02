package com.fssa.reparo.dto.booking;

import com.fssa.reparo.dto.vehicle.VehicleDTO;

public class BookingRequestDto extends VehicleDTO {
    private String problem;
    private boolean requestStatus;
    private boolean acceptStatus;
    private String bookingCity;
    private String bookingAddress;
    private String bookingState;

    public BookingRequestDto(int vehicleId, String problem, boolean requestStatus, boolean acceptStatus, String bookingCity, String bookingAddress, String bookingState) {
        super(vehicleId);
        this.problem = problem;
        this.requestStatus = requestStatus;
        this.acceptStatus = acceptStatus;
        this.bookingCity = bookingCity;
        this.bookingAddress = bookingAddress;
        this.bookingState = bookingState;
    }
    public BookingRequestDto(String problem, boolean requestStatus, boolean acceptStatus, String bookingCity, String bookingAddress, String bookingState) {
        this.problem = problem;
        this.requestStatus = requestStatus;
        this.acceptStatus = acceptStatus;
        this.bookingCity = bookingCity;
        this.bookingAddress = bookingAddress;
        this.bookingState = bookingState;
    }
    public BookingRequestDto(){

    }



    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public boolean isRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(boolean requestStatus) {
        this.requestStatus = requestStatus;
    }

    public boolean isAcceptStatus() {
        return acceptStatus;
    }

    public void setAcceptStatus(boolean acceptStatus) {
        this.acceptStatus = acceptStatus;
    }

    public String getBookingCity() {
        return bookingCity;
    }

    public void setBookingCity(String bookingCity) {
        this.bookingCity = bookingCity;
    }

    public String getBookingAddress() {
        return bookingAddress;
    }

    public void setBookingAddress(String bookingAddress) {
        this.bookingAddress = bookingAddress;
    }

    public String getBookingState() {
        return bookingState;
    }

    public void setBookingState(String bookingState) {
        this.bookingState = bookingState;
    }
}


