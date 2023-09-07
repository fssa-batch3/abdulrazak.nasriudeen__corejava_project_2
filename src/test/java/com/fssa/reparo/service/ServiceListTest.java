package com.fssa.reparo.service;
import com.fssa.reparo.dto.service.ServiceListResponseDto;
import com.fssa.reparo.dto.service.ServiceRequestDto;
import com.fssa.reparo.dto.service.ServiceResponseDto;
import com.fssa.reparo.exception.ServiceException;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)


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
            ServiceListResponseDto list =  serviceList.getServiceByBookingId(59);
            Assertions.assertEquals(59,list.getBookingInfo().getBookingId());
        } catch (ServiceException e) {

            throw new RuntimeException(e);
        }

    }
    @Test
    @Order(3)
    void getServiceListById(){
        try {
            Assertions.assertEquals(59,serviceList.getServiceById(18).getBookingInfo().getBookingId());
        } catch (ServiceException e) {
            fail();
            throw new RuntimeException(e);
        }

    }
@Test
@Order(4)
void updateServiceAmount(){
    try {
        Assertions.assertTrue(serviceList.updateServiceAmount(18,500));
    } catch (ServiceException e) {
        fail();
        throw new RuntimeException(e);
    }

}
    @Test
    @Order(5)
    void updateCancelService(){
        try {
            Assertions.assertTrue(serviceList.updateCancelService(23,true,"high Cost"));
        } catch (ServiceException e) {
            fail();
            throw new RuntimeException(e);
        }

    }
    @Test
    @Order(6)
    void updateAcceptService(){
        try {
            Assertions.assertTrue(serviceList.updateAcceptService(23,true));
        } catch (ServiceException e) {
            fail();
            throw new RuntimeException(e);
        }

    }
     @Test
     @Order(7)
     void addServiceTest(){
         try {
              ServiceRequestDto ser =  new ServiceRequestDto(18,30,"air");
             Assertions.assertTrue(serviceList.addService(ser));
         } catch (ServiceException e) {
             fail();
             throw new RuntimeException(e);
         }

     }
     @Test
     @Order(8)
     void updateServiceTest(){
         try {
             ServiceRequestDto ser =  new ServiceRequestDto(0,55,"air");
             Assertions.assertTrue(serviceList.updateServiceDetail(ser,20));
         } catch (ServiceException e) {
             fail();
             throw new RuntimeException(e);
         }

     }
     @Test
     @Order(9)
     void  deleteServiceTest(){
         try {
             Assertions.assertTrue(serviceList.deleteEachService(23));
         } catch (ServiceException e) {

             e.printStackTrace();
         }

     }
     @Test
     @Order(10)
     void getAllServiceTest(){
         try {
             List<ServiceListResponseDto> responseDto =  serviceList.getAllServiceLists();
             Assertions.assertEquals("air",responseDto.get(0).getListOfServices().get(0).getServiceInfo().getServiceName());
         } catch ( ServiceException e) {

             throw new RuntimeException(e);
         }

     }

     @Test
     @Order(11)
     void getEachServiceTest(){
         try {

             ServiceResponseDto resp =  serviceList.getEachServiceById(20);
             Assertions.assertEquals("air",resp.getServiceInfo().getServiceName());
         } catch ( ServiceException e) {

             throw new RuntimeException(e);
         }

     }







}
