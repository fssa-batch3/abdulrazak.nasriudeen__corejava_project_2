package service;
import dao.UserDao;
import exception.DAOException;
import exception.ServiceException;
import model.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
public class UserServiceTest {
    @BeforeAll
    static  void createUser(){
        User use = new User("Razak",9840326515L,"abd123");
        UserServices user  = new UserServices();
        try {
            Assertions.assertTrue(user.registerUser(use));
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }


    }

    @Test
    void createUserSuccess(){
        UserDao userDao = new UserDao();
        try {
            User us = userDao.findUserByNumber(9840326515L);
            Assertions.assertEquals("Razak",us.getName());
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
           Assertions.assertEquals(10,user.loginUser(9840326510L ,"abd123"));
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

    }
    @Test
    void loginTestFail(){
        UserServices user =  new UserServices();
        try {
            Assertions.assertFalse(user.loginUser(9840326510L ,"abd123")!=10);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

    }
    @Test
    void getUserByIdTestSuccess(){
        UserServices user =  new UserServices();

        try {
        User use = user.getUserById(10);
            Assertions.assertEquals("Abdul",use.getName());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

    }
    @Test
    void getUserByIdTestFail(){
        UserServices user =  new UserServices();

        try {
            User use = user.getUserById(10);
            Assertions.assertNotEquals("Abdu", use.getName());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

    }
    @Test
    void getAllUsersTestSuccess(){
        UserServices user =  new UserServices();
        try {
            ArrayList<User> users = user.getAllUsers();
            Assertions.assertFalse(users.isEmpty());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }


    }

    @AfterAll
    static void deleteTestUser(){
        UserDao dao  = new UserDao();
        try {
            Assertions.assertTrue(dao.removeUser(9840326515L));
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }

    }

}
