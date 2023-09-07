package com.fssa.reparo.service;
import com.fssa.reparo.dao.WorkShopDao;
import com.fssa.reparo.dto.workshop.WorkShopRequestDto;
import com.fssa.reparo.dto.workshop.WorkShopResponseDto;
import com.fssa.reparo.exception.DAOException;
import com.fssa.reparo.exception.ServiceException;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

class WorkShopServiceTest {
     protected  WorkShopService workService = new WorkShopService();


     @Test
     @Order(1)
     void createWorkshopTest(){
         WorkShopRequestDto request = new WorkShopRequestDto("Auto mobiles",9840326000L,"abd234",2,"123  Main Street","chennai","tamilNadu");
         WorkShopService workService =  new WorkShopService();
         try {
             Assertions.assertTrue(workService.registerWorkShop(request));
         } catch (ServiceException e) {
             throw new RuntimeException(e);
         }

     }
     @Test
    @Order(2)
    void loginWorkshopTest(){
        try{
            Assertions.assertEquals(18,workService.loginWorkShop(9840326580L,"abd123"));
        }catch (ServiceException e){
            throw new RuntimeException(e);

        }

    }
    @Test
    @Order(3)
    void logoutWorkShopTest(){
        try {
            Assertions.assertTrue(workService.logOutWorkShop(18));
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    @Order(4)
    void getWorkShopByAreaTest(){

        try {
            Assertions.assertFalse(workService.getWorkShopByArea("chennai").isEmpty());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }


    }
    @Test
    @Order(5)
    void getWorkShopByTypeTest(){

        try {
            Assertions.assertFalse(workService.getWorkShopByType(2).isEmpty());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }


    }
    @Test
    @Order(6)
    void getAllWorkShopTest(){

        try {
            Assertions.assertFalse(workService.getAllWorkShop().isEmpty());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }


    }
    @Test
    @Order(7)
    void getWorkShopByIdTest(){

        try {
            WorkShopResponseDto response = workService.getWorkShopById(18);
            Assertions.assertEquals("Auto mobiles", response.getWorkshopName() );
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

    }
    @Test
    @Order(8)
    void updatePasswordTest(){
        try {
            Assertions.assertTrue(workService.updateWorkshopPassword(9840326580L,"abd123"));
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

    }
     @Test
     @Order(9)
     void updatePasswordTestFails(){

         ServiceException exception = assertThrows(ServiceException.class, () -> workService.updateWorkshopPassword(9840326580L,"Test"));
         String[] arr = exception.getMessage().split(":");
         assertEquals(" Invalid Password", arr[arr.length - 1]);

     }
     @Test
    @Order(10)
    void removeWorkshopTest(){
         WorkShopDao workDao =  new WorkShopDao();
        try {
            Assertions.assertTrue(workDao.removeWorkShopAccount(9840326000L));
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }


     }

}
