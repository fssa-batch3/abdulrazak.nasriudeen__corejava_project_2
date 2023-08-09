package dao;
import exception.DaoException;
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
            WorkShopDao.insertWorkShop(use);
        } catch (DaoException e) {
            e.printStackTrace();

        }

    }


    @Test
    void insertWorkShopSuccess() {
        try {

            WorkShop us = WorkShopDao.findWorkShopByNumber(98403265105L);
            Assertions.assertEquals(98403265105L, us.getNumber());


        } catch (DaoException e) {
            e.printStackTrace();

        }
    }

    @Test
    void testUpdatePassword() {
        try {

            Assertions.assertTrue(WorkShopDao.updateWorkShopPassword(98403265105L, "test"));
            WorkShop us = WorkShopDao.findWorkShopByNumber(98403265105L);
            Assertions.assertEquals("test", us.getPassword());


        } catch (DaoException e) {
            e.printStackTrace();

        }
    }

    @Test
    void getAllWorkShops() {
        try {
            ArrayList<WorkShop> workShop = WorkShopDao.getAllWorkShops();
            Assertions.assertTrue(workShop.size() != 0);
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
    }

        @AfterAll
       static void deleteWorkshop()
        {
            try {

                Assertions.assertTrue(WorkShopDao.removeWorkShopAccount(98403265105L));

            }catch (DaoException e){
                e.printStackTrace();

            }
        }

}
