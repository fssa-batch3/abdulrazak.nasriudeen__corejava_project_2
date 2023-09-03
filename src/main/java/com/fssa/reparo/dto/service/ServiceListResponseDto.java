package com.fssa.reparo.dto.service;

import com.fssa.reparo.dto.booking.BookingResponseInclAcceptDto;

import java.util.List;

public class ServiceListResponseDto{
    private List<ServiceResponseDto> listOfServices ;
    private BookingResponseInclAcceptDto bookingInfo;

        private int serviceListId;

    public ServiceListResponseDto() {
    }

    private int serviceAmount;
        private boolean acceptStatus;
        private boolean cancelStatus;
        private String cancelReason;

        public ServiceListResponseDto(List<ServiceResponseDto> listOfServices,BookingResponseInclAcceptDto bookingInfo ,int serviceListId, int serviceAmount, boolean acceptStatus, boolean cancelStatus, String cancelReason) {
            this.serviceListId = serviceListId;
            this.serviceAmount = serviceAmount;
            this.acceptStatus = acceptStatus;
            this.cancelStatus = cancelStatus;
            this.cancelReason = cancelReason;
            this.bookingInfo = bookingInfo;
            this.listOfServices = listOfServices;
        }
        public int getServiceListId() {
            return serviceListId;
        }
        public void setServiceListId(int serviceListId) {
            this.serviceListId = serviceListId;
        }

        public int getServiceAmount() {
            return serviceAmount;
        }

        public void setServiceAmount(int serviceAmount) {
            this.serviceAmount = serviceAmount;
        }



        public boolean isAcceptStatus() {
            return acceptStatus;
        }

        public void setAcceptStatus(boolean acceptStatus) {
            this.acceptStatus = acceptStatus;
        }

        public boolean isCancelStatus() {
            return cancelStatus;
        }

        public void setCancelStatus(boolean cancelStatus) {
            this.cancelStatus = cancelStatus;
        }
        public String getCancelReason() {
            return cancelReason;
        }

        public void setCancelReason(String cancelReason) {
            this.cancelReason = cancelReason;
        }


    public ServiceListResponseDto(List<ServiceResponseDto> listOfServices,BookingResponseInclAcceptDto bookingInfo ) {
        
        this.bookingInfo = bookingInfo;
        this.listOfServices = listOfServices;
    }

    public BookingResponseInclAcceptDto getBookingInfo() {
        return bookingInfo;
    }

    public void setBookingInfo(BookingResponseInclAcceptDto bookingInfo) {
        this.bookingInfo = bookingInfo;
    }

    public List<ServiceResponseDto> getListOfServices() {
        return listOfServices;
    }

    public void setListOfServices(List<ServiceResponseDto> listOfServices) {
        this.listOfServices = listOfServices;
    }
}
