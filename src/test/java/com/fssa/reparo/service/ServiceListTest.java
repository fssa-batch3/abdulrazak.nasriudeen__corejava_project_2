package com.fssa.reparo.service;
import com.fssa.reparo.exception.ServiceException;
import com.fssa.reparo.model.ServiceList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

 class ServiceListTest {
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
    @Test
    @Order(3)
    void getServiceListById(){
        try {
            Assertions.assertEquals(18,serviceList.getServiceById(1).getBookingId());
        } catch (ServiceException e) {
            fail();
            throw new RuntimeException(e);
        }

    }
@Test
@Order(4)
void updateServiceAmount(){
    try {
        Assertions.assertTrue(serviceList.updateServiceAmount(1,500));
    } catch (ServiceException e) {
        fail();
        throw new RuntimeException(e);
    }

}
    @Test
    @Order(5)
    void updateCancelService(){
        try {
            Assertions.assertTrue(serviceList.updateCancelService(1,true,"high Cost"));
        } catch (ServiceException e) {
            fail();
            throw new RuntimeException(e);
        }

    }
    @Test
    @Order(6)
    void updateAcceptService(){
        try {
            Assertions.assertTrue(serviceList.updateAcceptService(1,true));
        } catch (ServiceException e) {
            fail();
            throw new RuntimeException(e);
        }

    }
     @Test
     @Order(6)
     void addServiceTest(){
         try {
             ServiceList ser =  new ServiceList();
             ser.setServiceName("punchre");
             ser.setPrice(30);
             ser.setServiceListId(1);
             Assertions.assertTrue(serviceList.addService(ser));
         } catch (ServiceException e) {
             fail();
             throw new RuntimeException(e);
         }

     }
     @Test
     @Order(6)
     void updateServiceTest(){
         try {
             ServiceList ser =  new ServiceList();
             ser.setServiceName("Air");
             ser.setPrice(80);
             ser.setServiceListId(1);
             ser.setServiceId(2);
             Assertions.assertTrue(serviceList.updateServiceDetail(ser));
         } catch (ServiceException e) {
             fail();
             throw new RuntimeException(e);
         }

     }


}
