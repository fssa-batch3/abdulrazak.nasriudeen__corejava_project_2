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
    private final UserServices userServices =  new UserServices();

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
        try {
            Assertions.assertFalse(userServices.registerUser(use));
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

    }
    @Test
    void loginTestSuccess(){
        try {
            Assertions.assertEquals(35,userServices.loginUser(9840326515L ,"pas123"));
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

    }
    @Test
    void loginTestFail(){
        try {
            Assertions.assertNotEquals(20,userServices.loginUser(9840326515L ,"pas123"));
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

    }
    @Test
    void getUserByIdTestSuccess(){

        try {
            User use = userServices.getUserById(35);
            Assertions.assertEquals("Razak Test",use.getName());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

    }
    @Test
    void getUserByIdTestFail(){
        try {
            User use = userServices.getUserById(24);
            Assertions.assertNotEquals("Abdu", use.getName());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void getAllUsersTestSuccess(){
        try {
            List<User> users = userServices.getAllUsers();
            Assertions.assertFalse(users.isEmpty());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }


    }

    @Test
    void updateUserPassword(){
        try {
          Assertions.assertTrue(userServices.updateUserPassword("pas123",9840326515L));
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void updateUserPasswordFail(){
        try {
            Assertions.assertFalse(userServices.updateUserPassword("chennai",9840326515L));
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void logoutSuccessTest(){
        try {
            Assertions.assertTrue(userServices.logOutUser(35));
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void logoutFailTest(){
        try {
            Assertions.assertFalse(userServices.logOutUser(20));
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