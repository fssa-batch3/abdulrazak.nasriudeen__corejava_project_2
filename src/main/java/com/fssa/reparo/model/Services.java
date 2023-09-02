package com.fssa.reparo.model;

import java.util.List;

public class Services extends ServicesEntities {
    private Booking booking;
    private List<ServiceList> serviceList;

    public List<ServiceList> getServices() {
        return serviceList;
    }

    public void setServices(List<ServiceList> services) {
        this.serviceList = services;
    }

    public Booking getBooking() {
        return booking;
    }
    public void setBooking(Booking booking) {
        this.booking = booking;
    }

}
