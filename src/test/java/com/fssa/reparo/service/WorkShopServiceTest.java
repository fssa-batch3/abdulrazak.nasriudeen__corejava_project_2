package com.fssa.reparo.service;
import com.fssa.reparo.dao.WorkShopDAO;
import com.fssa.reparo.dto.WorkShopDTO;
import com.fssa.reparo.exception.DAOException;
import com.fssa.reparo.exception.ServiceException;
import org.junit.jupiter.api.*;

class WorkShopServiceTest {
     protected  WorkShopService workService = new WorkShopService();
     @BeforeAll
     static  void createWorkshop(){
         WorkShopDTO work = new WorkShopDTO();
         work.setWorkshopName("Auto mobiles");
         work.setWorkshopNumber(9840326000L);
         work.setWorkshopPassword("abd234");
         work.setWorkshopAddress("123  Main Street");
         work.setCity("chennai");
         work.setState("tamilNadu");
         work.setWorkshopType(2);
         WorkShopService workService =  new WorkShopService();
         try {
             Assertions.assertTrue(workService.registerWorkShop(work));
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
            WorkShopDTO work = workService.getWorkShopById(18);
            Assertions.assertEquals("Auto mobiles", work.getWorkshopName() );
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
