package dao;
import exception.DaoException;
import model.WorkShop;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class WorkshopDaoTest {

        @BeforeAll
        static void testWorkShopInsert(){
            WorkShop use = new WorkShop();
        use.setName("Abdul");
        use.setNumber(98403265109L);
        use.setPassword("1234");
        try {
            WorkShopDao.insertWorkShop(use);
        }catch (DaoException e){
            e.printStackTrace();

        }

    }


        @Test
        void insertWorkShopSuccess(){
        try {

            WorkShop us = WorkShopDao.findWorkShopByNumber(98403265109L);
            Assertions.assertEquals(98403265109L,us.getNumber());
            // Assertions.assertTrue(UserDao.deleteUserAccount(use.getNumber()));

        }catch (DaoException e){
            e.printStackTrace();

        }
    }
        @Test
        void testUpdatePassword(){
        try {

            Assertions.assertTrue(WorkShopDao.updateWorkShopPassword(98403265109L,"test"));
            WorkShop us = WorkShopDao.findWorkShopByNumber(98403265109L);
            Assertions.assertEquals("test",us.getPassword());


        }catch (DaoException e){
            e.printStackTrace();

        }
    }
        @Test
        void deleteUser()
        {
            try {

                Assertions.assertTrue(WorkShopDao.removeWorkShopAccount(98403265109L));

            }catch (DaoException e){
                e.printStackTrace();

            }
        }
    }

