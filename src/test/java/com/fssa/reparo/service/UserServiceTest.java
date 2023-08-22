package com.fssa.reparo.service;
import com.fssa.reparo.dao.UserDao;
import com.fssa.reparo.exception.DAOException;
import com.fssa.reparo.exception.ServiceException;
import com.fssa.reparo.model.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.List;

class UserServiceTest {

    @BeforeAll
    static  void createUser(){
        User use = new User();
        use.setName("Razak");
        use.setNumber(9840326001L);
        use.setPassword("abd123");
        UserServices user  = new UserServices();
        try {
            user.registerUser(use);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }


    }
    @Test
    void createUserSuccess(){
        UserDao userDao = new UserDao();
        try {
            User us = userDao.findUserByNumber(9840326001L);
            Assertions.assertEquals("Razak",(us.getName()));


        } catch (DAOException e) {
            throw new RuntimeException(e);
        }

    }




    @Test
    void createUserTestFail (){
        User use = new User("Abdul",98403265109L,"123456");
        UserServices user =  new UserServices();
        try {
            Assertions.assertFalse(user.registerUser(use));
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

    }
    @Test
    void loginTestSuccess(){
        UserServices user =  new UserServices();
        try {
            Assertions.assertEquals(35,user.loginUser(9840326515L ,"abd123"));
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

    }
    @Test
    void loginTestFail(){
        UserServices user =  new UserServices();
        try {
            Assertions.assertNotEquals(20,user.loginUser(9840326515L ,"abd123"));
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

    }
    @Test
    void getUserByIdTestSuccess(){
        UserServices user =  new UserServices();

        try {
            User use = user.getUserById(35);
            Assertions.assertEquals("Razak Test",use.getName());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

    }
    @Test
    void getUserByIdTestFail(){
        UserServices user =  new UserServices();

        try {
            User use = user.getUserById(24);
            Assertions.assertNotEquals("Abdu", use.getName());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void getAllUsersTestSuccess(){
        UserServices user =  new UserServices();
        try {
            List<User> users = user.getAllUsers();
            Assertions.assertFalse(users.isEmpty());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }


    }
    @AfterAll
    static void deleteTestUser() {
        UserDao dao = new UserDao();
        try {
            Assertions.assertTrue(dao.removeUser(9840326001L));
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }


    }
}