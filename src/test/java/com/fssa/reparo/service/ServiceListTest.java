package com.fssa.reparo.service;
import com.fssa.reparo.dao.ServiceDao;
import com.fssa.reparo.dto.service.ServiceListResponseDto;
import com.fssa.reparo.exception.DAOException;
import com.fssa.reparo.exception.ServiceException;
import com.fssa.reparo.model.ServiceList;
import com.fssa.reparo.model.Services;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

 class ServiceListTest {
    ServiceListServices serviceList =  new ServiceListServices();
    @Test
    @Order(1)
    void createServiceList(){
        try {
            Assertions.assertTrue(serviceList.createServiceList(22));
        } catch (ServiceException e) {
            fail();
            throw new RuntimeException(e);
        }

    }
    @Test
    @Order(2)
    void getServiceListByBookingId(){
        try {
            ServiceListResponseDto list =  serviceList.getServiceByBookingId(22);
            Assertions.assertEquals(22,list.getBookingInfo().getBookingId());
        } catch (ServiceException e) {

            throw new RuntimeException(e);
        }

    }
    @Test
    @Order(3)
    void getServiceListById(){
        try {
            Assertions.assertEquals(22,serviceList.getServiceById(2).getBookingInfo().getBookingId());
        } catch (ServiceException e) {
            fail();
            throw new RuntimeException(e);
        }

    }
@Test
@Order(4)
void updateServiceAmount(){
    try {
        Assertions.assertTrue(serviceList.updateServiceAmount(2,500));
    } catch (ServiceException e) {
        fail();
        throw new RuntimeException(e);
    }

}
    @Test
    @Order(5)
    void updateCancelService(){
        try {
            Assertions.assertTrue(serviceList.updateCancelService(2,true,"high Cost"));
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
             ser.setServiceName("air");
             ser.setPrice(30);
             ser.setServiceListId(2);
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
     @Test
     @Order(6)
     void  deleteServiceTest(){
         try {
             Assertions.assertTrue(serviceList.deleteEachService(1));
         } catch (ServiceException e) {

             throw new RuntimeException(e);
         }

     }
     @Test
     @Order(6)
     void getAllServiceTest(){
         try {
             ServiceDao dao =  new ServiceDao();
             List<ServiceListResponseDto> responseDto =  serviceList.getAllServiceLists();
             Assertions.assertEquals("Air",responseDto.get(0).getListOfServices().get(0).getServiceInfo().getServiceName());
         } catch ( ServiceException e) {

             throw new RuntimeException(e);
         }

     }





}
