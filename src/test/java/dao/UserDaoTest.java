package dao;
import exception.DaoException;
import model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class UserDaoTest {
    @BeforeAll
    static void testUserInsert(){
        User use = new User();
        use.setName("Abdul");
        use.setNumber(98403265109L);
        use.setPassword("1234");
        try {
            UserDao.insertUser(use);
        }catch (DaoException e){
            e.printStackTrace();

        }

    }


    @Test
    void insertUserSuccess(){
        try {

            User us = UserDao.findUserByNumber(98403265109L);
            Assertions.assertEquals(98403265109L,us.getNumber());

        }catch (DaoException e){
            e.printStackTrace();

        }
    }
    @Test
    void testUpdatePassword(){
        try {

           Assertions.assertTrue(UserDao.updateUserPassword(98403265109L,"test"));
            User us = UserDao.findUserByNumber(98403265109L);
            Assertions.assertEquals("test",us.getPassword());


        }catch (DaoException e){
            e.printStackTrace();

        }
          }
    @Test
    void deleteUser()
    {
        try {

            Assertions.assertTrue(UserDao.removeUser(98403265109L));

        }catch (DaoException e){
            e.printStackTrace();

        }
    }
    @Test
    void getAllUserTest(){

        try {
            ArrayList<User> users = UserDao.getAllUser();
            Assertions.assertTrue(users.isEmpty());
        } catch (DaoException e) {
           e.printStackTrace();
        }
    }

}

