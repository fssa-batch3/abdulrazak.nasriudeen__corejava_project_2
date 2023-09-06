package com.fssa.reparo.service;
import com.fssa.reparo.dto.service.ServiceListResponseDto;
import com.fssa.reparo.dto.service.ServiceRequestDto;
import com.fssa.reparo.dto.service.ServiceResponseDto;
import com.fssa.reparo.exception.ServiceException;
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
            Assertions.assertTrue(serviceList.updateCancelService(18,true,"high Cost"));
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
              ServiceRequestDto ser =  new ServiceRequestDto(2,30,"air");
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
             ServiceRequestDto ser =  new ServiceRequestDto(0,55,"air");
             Assertions.assertTrue(serviceList.updateServiceDetail(ser,3));
         } catch (ServiceException e) {
             fail();
             throw new RuntimeException(e);
         }

     }
     @Test
     @Order(6)
     void  deleteServiceTest(){
         try {
             Assertions.assertTrue(serviceList.deleteEachService(2));
         } catch (ServiceException e) {

             e.printStackTrace();
         }

     }
     @Test
     @Order(6)
     void getAllServiceTest(){
         try {
             List<ServiceListResponseDto> responseDto =  serviceList.getAllServiceLists();
             System.out.print(responseDto.get(0).getCancelReason());
             Assertions.assertEquals("plug replacement",responseDto.get(0).getListOfServices().get(0).getServiceInfo().getServiceName());
         } catch ( ServiceException e) {

             throw new RuntimeException(e);
         }

     }
     @Test
     @Order(6)
     void getEachServiceTest(){
         try {

             ServiceResponseDto resp =  serviceList.getEachServiceById(3);
             Assertions.assertEquals("punchre",resp.getServiceInfo().getServiceName());
         } catch ( ServiceException e) {

             throw new RuntimeException(e);
         }

     }





}
