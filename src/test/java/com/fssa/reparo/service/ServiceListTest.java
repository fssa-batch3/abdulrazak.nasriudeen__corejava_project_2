package com.fssa.reparo.service;

import com.fssa.reparo.exception.ServiceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class ServiceListTest {
    ServiceListServices serviceList =  new ServiceListServices();
    @Test
    @Order(1)
    void createServiceList(){
        try {
            Assertions.assertTrue(serviceList.createServiceList(18));
        } catch (ServiceException e) {
            fail();
            throw new RuntimeException(e);
        }

    }
    @Test
    @Order(2)
    void getServiceListByBookingId(){
        try {
            Assertions.assertEquals(18,serviceList.getServiceByBookingId(18).getBookingId());
        } catch (ServiceException e) {
            fail();
            throw new RuntimeException(e);
        }

    }

}
