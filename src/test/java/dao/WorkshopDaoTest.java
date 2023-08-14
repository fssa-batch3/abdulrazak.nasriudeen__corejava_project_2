package dao;
import exception.DAOException;
import model.WorkShop;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class WorkshopDaoTest {

    @BeforeAll
    static void testWorkShopInsert() {
        WorkShop use = new WorkShop();
        use.setName("Abdul");
        use.setNumber(98403265105L);
        use.setPassword("1234");
        try {
            WorkShopDao work = new WorkShopDao();
            work.insertWorkShop(use);
        } catch (DAOException e) {
            e.printStackTrace();

        }

    }


    @Test
    void insertWorkShopSuccess() {
        try {
            WorkShopDao work  =  new WorkShopDao() ;

            WorkShop us = work.findWorkShopByNumber(98403265105L);
            Assertions.assertEquals(98403265105L, us.getNumber());


        } catch (DAOException e) {
            e.printStackTrace();

        }
    }

    @Test
    void testUpdatePassword() {
        try {
            WorkShopDao work  =  new WorkShopDao() ;
            Assertions.assertTrue(work.updateWorkShopPassword(98403265105L, "test"));
            WorkShop us = work.findWorkShopByNumber(98403265105L);
            Assertions.assertEquals("test", us.getPassword());


        } catch (DAOException e) {
            e.printStackTrace();

        }
    }

    @Test
    void getAllWorkShops() {
        try {
            WorkShopDao work  =  new WorkShopDao() ;
            ArrayList<WorkShop> workShop = work.getAllWorkShops();
            Assertions.assertTrue(workShop.size() != 0);
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void getWorkshopByAreaTest(){
        try {
            WorkShopDao bookDao = new WorkShopDao();
            ArrayList<Integer> arr = bookDao.findWorkshopsByArea("chennai");
            Assertions.assertEquals(14,arr.get(0));
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }

    }

        @AfterAll
       static void deleteWorkshop()
        {
            try {
                WorkShopDao work  =  new WorkShopDao() ;
                Assertions.assertTrue(work.removeWorkShopAccount(98403265105L));

            }catch (DAOException e){
                e.printStackTrace();

            }
        }

}
