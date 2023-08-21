package com.fssa.reparo.dao;

import com.fssa.reparo.exception.DAOException;
import com.fssa.reparo.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

 class UserDaoTest {
    @BeforeAll
    static void testUserInsert(){
        User use = new User();
        use.setName("Abdul");
        use.setNumber(98403265109L);
        use.setPassword("1234");
        UserDao userTest = new UserDao();
        try {
            userTest.insertUser(use);
        }catch (DAOException e){
            e.printStackTrace();

        }

    }


    @Test
    void insertUserSuccess(){
        try {
            UserDao userTest = new UserDao();
            User us = userTest.findUserByNumber(98403265109L);
            Assertions.assertEquals(98403265109L,us.getNumber());

        }catch (DAOException e){
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


        }catch (DAOException e){
            e.printStackTrace();

        }
          }
    @Test
    void deleteUser()
    {
        try {
            UserDao userTest = new UserDao();

            Assertions.assertTrue(userTest.removeUser(98403265109L));

        }catch (DAOException e){
            e.printStackTrace();

        }
    }
    @Test
    void getAllUserTest(){

        try {
            UserDao userTest = new UserDao();
            List<User> users = userTest.getAllUser();
            Assertions.assertFalse(users.isEmpty());
        } catch (DAOException e) {
           e.printStackTrace();
        }
    }

}

