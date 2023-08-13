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
        UserDao userTest = new UserDao();
        try {
            userTest.insertUser(use);
        }catch (DaoException e){
            e.printStackTrace();

        }

    }


    @Test
    void insertUserSuccess(){
        try {
            UserDao userTest = new UserDao();
            User us = userTest.findUserByNumber(98403265109L);
            Assertions.assertEquals(98403265109L,us.getNumber());

        }catch (DaoException e){
            e.printStackTrace();

        }
    }
    @Test
    void testUpdatePassword(){
        try {
            UserDao userTest = new UserDao();

           Assertions.assertTrue(userTest.updateUserPassword(98403265109L,"test"));
            User us = userTest.findUserByNumber(98403265109L);
            Assertions.assertEquals("test",us.getPassword());


        }catch (DaoException e){
            e.printStackTrace();

        }
          }
    @Test
    void deleteUser()
    {
        try {
            UserDao userTest = new UserDao();

            Assertions.assertTrue(userTest.removeUser(98403265109L));

        }catch (DaoException e){
            e.printStackTrace();

        }
    }
    @Test
    void getAllUserTest(){

        try {
            UserDao userTest = new UserDao();
            ArrayList<User> users = userTest.getAllUser();
            Assertions.assertTrue(users.isEmpty());
        } catch (DaoException e) {
           e.printStackTrace();
        }
    }

}

