package com.fssa.reparo.dao;
import com.fssa.reparo.exception.DAOException;
import com.fssa.reparo.model.WorkShop;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

 class WorkshopDaoTest {

    @BeforeAll
    static void testWorkShopInsert() {
        WorkShop use = new WorkShop();
        use.setName("Abdul");
        use.setNumber(98403265105L);
        use.setPassword("1234");
        try {
            WorkShopDAO work = new WorkShopDAO();
            work.insertWorkShop(use);
        } catch (DAOException e) {
            e.printStackTrace();

        }

    }


    @Test
    void insertWorkShopSuccess() {
        try {
            WorkShopDAO work  =  new WorkShopDAO() ;

            WorkShop us = work.findWorkShopByNumber(98403265105L);
            Assertions.assertEquals(98403265105L, us.getNumber());


        } catch (DAOException e) {
            e.printStackTrace();

        }
    }



    @Test
    void getAllWorkShops() {
        try {
            WorkShopDAO work  =  new WorkShopDAO() ;
            List<WorkShop> workShop = work.getAllWorkShops();
            Assertions.assertNotEquals(0,workShop.size());
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void getWorkshopByAreaTest(){
        try {
            WorkShopDAO bookDao = new WorkShopDAO();
            List<Integer> arr = bookDao.findWorkshopsByArea("chennai");
            Assertions.assertEquals(14,arr.get(0));
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }

    }

        @AfterAll
       static void deleteWorkshop()
        {
            try {
                WorkShopDAO work  =  new WorkShopDAO() ;
                Assertions.assertTrue(work.removeWorkShopAccount(98403265105L));

            }catch (DAOException e){
                e.printStackTrace();

            }
        }

}
