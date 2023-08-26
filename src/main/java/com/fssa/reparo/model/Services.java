package com.fssa.reparo.model;

public class Services extends ServicesEntities {
    private Booking booking;
    private WorkShop serviceWorkshop;
    public Booking getBooking() {
        return booking;
    }
    public void setBooking(Booking booking) {
        this.booking = booking;
    }
    public WorkShop getServiceWorkshop() {
        return serviceWorkshop;
    }

    public void setServiceWorkshop(WorkShop serviceWorkshop) {
        this.serviceWorkshop = serviceWorkshop;
    }
}
