package com.fssa.reparo.model;

public class ServicesEntities {
    private int serviceListId;
    private int serviceAmount;
    private int bookingId;
    private boolean acceptStatus;
    private boolean cancelStatus;
    private boolean isLive;

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

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
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

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }
}
