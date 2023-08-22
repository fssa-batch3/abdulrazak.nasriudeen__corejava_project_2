package com.fssa.reparo.model;

public class ServicesEntities {
    private int serviceId;
    private int bookingId;
    private int workshopId;
    private int serviceAmount;
    private boolean acceptStatus;
    private boolean isLive;

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    public boolean isAcceptStatus() {
        return acceptStatus;
    }

    public void setAcceptStatus(boolean acceptStatus) {
        this.acceptStatus = acceptStatus;
    }

    public int getServiceAmount() {
        return serviceAmount;
    }

    public void setServiceAmount(int serviceAmount) {
        this.serviceAmount = serviceAmount;
    }

    public int getWorkshopId() {
        return workshopId;
    }

    public void setWorkshopId(int workshopId) {
        this.workshopId = workshopId;
    }



    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }


    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }



}
