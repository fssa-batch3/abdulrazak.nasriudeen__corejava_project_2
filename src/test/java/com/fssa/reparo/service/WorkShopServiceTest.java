package com.fssa.reparo.service;
import com.fssa.reparo.dao.WorkShopDao;
import com.fssa.reparo.exception.DAOException;
import com.fssa.reparo.exception.ServiceException;
import com.fssa.reparo.model.WorkShop;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
 class WorkShopServiceTest {
     protected  WorkShopService workService = new WorkShopService();
     @BeforeAll
     static  void createWorkshop(){
         WorkShop work = new WorkShop();
         work.setName("Auto mobiles");
         work.setNumber(9840326000L);
         work.setPassword("abd234");
         work.setAddress("123  Main Street");
         work.setCity("chennai");
         work.setState("tamilNadu");
         work.setType(2);
         WorkShopService workService =  new WorkShopService();
         try {
             Assertions.assertTrue(workService.registerWorkShop(work));
         } catch (ServiceException e) {
             throw new RuntimeException(e);
         }

     }

    @Test
    void loginWorkshopTest(){
        try{
            Assertions.assertEquals(18,workService.loginWorkShop(9840326580L,"abd234"));
        }catch (ServiceException e){
            throw new RuntimeException(e);

        }

    }
    @Test
    void getWorkShopByAreaTest(){
        WorkShopService ser   = new WorkShopService();
        try {
            Assertions.assertFalse(ser.getWorkShopByArea("chennai").isEmpty());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }


    }
    @Test
    void getWorkShopByTypeTest(){
        WorkShopService ser   = new WorkShopService();
        try {
            Assertions.assertFalse(ser.getWorkShopByType(2).isEmpty());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }


    }
    @Test
    void getAllWorkShopTest(){
        WorkShopService ser   = new WorkShopService();
        try {
            Assertions.assertFalse(ser.getAllWorkShop().isEmpty());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }


    }
    @Test
    void getWorkShopByIdTest(){
        WorkShopService ser   = new WorkShopService();
        try {
            WorkShop work = ser.getWorkShopById(18);
            Assertions.assertEquals("Auto mobiles", work.getName() );
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

    }
    @AfterAll
     static void removeWorkshop(){
        WorkShopDao workDao =  new WorkShopDao();
        try {
            Assertions.assertTrue(workDao.removeWorkShopAccount(9840326000L));
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }


    }

}
