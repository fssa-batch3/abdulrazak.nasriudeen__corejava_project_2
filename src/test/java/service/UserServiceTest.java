package service;
import exception.ServiceException;
import model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
public class UserServiceTest {

    @Test
    void createUserTestFail (){
        User use = new User();
        use.setName("Abdul");
        use.setNumber(98403265109L);
        use.setPassword("123456");
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

}
