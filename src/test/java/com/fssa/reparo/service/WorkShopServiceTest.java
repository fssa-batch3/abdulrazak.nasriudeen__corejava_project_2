package com.fssa.reparo.service;
import com.fssa.reparo.dao.WorkShopDAO;
import com.fssa.reparo.dto.workshop.WorkShopRequestDto;
import com.fssa.reparo.dto.workshop.WorkShopResponseDto;
import com.fssa.reparo.exception.DAOException;
import com.fssa.reparo.exception.ServiceException;
import org.junit.jupiter.api.*;

class WorkShopServiceTest {
     protected  WorkShopService workService = new WorkShopService();
     @BeforeAll
     static  void createWorkshop(){
         WorkShopRequestDto request = new WorkShopRequestDto("Auto mobiles",9840326000L,"abd234",2,"123  Main Street","chennai","tamilNadu");
         WorkShopService workService =  new WorkShopService();
         try {
             Assertions.assertTrue(workService.registerWorkShop(request));
         } catch (ServiceException e) {
             throw new RuntimeException(e);
         }

     }



    @Test
    @Order(1)
    void loginWorkshopTest(){
        try{
            Assertions.assertEquals(18,workService.loginWorkShop(9840326580L,"abd123"));
        }catch (ServiceException e){
            throw new RuntimeException(e);

        }

    }
    @Test
    @Order(2)
    void logoutWorkShopTest(){
        try {
            Assertions.assertTrue(workService.logOutWorkShop(18));
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void getWorkShopByAreaTest(){

        try {
            Assertions.assertFalse(workService.getWorkShopByArea("chennai").isEmpty());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }


    }
    @Test
    void getWorkShopByTypeTest(){

        try {
            Assertions.assertFalse(workService.getWorkShopByType(2).isEmpty());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }


    }
    @Test
    void getAllWorkShopTest(){

        try {
            Assertions.assertFalse(workService.getAllWorkShop().isEmpty());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }


    }
    @Test
    void getWorkShopByIdTest(){

        try {
            WorkShopResponseDto response = workService.getWorkShopById(18);
            Assertions.assertEquals("Auto mobiles", response.getWorkshopName() );
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

    }
    @Test
    void updatePasswordTest(){
        try {
            Assertions.assertTrue(workService.updateWorkshopPassword(9840326580L,"abd123"));
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

    }
     @Test
     void updatePasswordTestFails(){
         try {
             Assertions.assertFalse(workService.updateWorkshopPassword(9840326580L,"Test"));
         } catch (ServiceException e) {
             throw new RuntimeException(e);
         }

     }
    @AfterAll
     static void removeWorkshop(){
        WorkShopDAO workDao =  new WorkShopDAO();
        try {
            Assertions.assertTrue(workDao.removeWorkShopAccount(9840326000L));
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }


    }

}
