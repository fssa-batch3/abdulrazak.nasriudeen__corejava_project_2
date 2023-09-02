package com.fssa.reparo.model;

import java.util.List;

public class Services extends ServicesEntities {
    private Booking booking;
    private List<ServiceList> services;

    public List<ServiceList> getServices() {
        return services;
    }

    public void setServices(List<ServiceList> services) {
        this.services = services;
    }

    public Booking getBooking() {
        return booking;
    }
    public void setBooking(Booking booking) {
        this.booking = booking;
    }

}
