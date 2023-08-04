package dao;
import exception.DaoException;
import model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

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
           // Assertions.assertTrue(UserDao.deleteUserAccount(use.getNumber()));

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

            Assertions.assertTrue(UserDao.deleteUserAccount(98403265109L));

        }catch (DaoException e){
            e.printStackTrace();

        }
    }
    @Test
    void addUserVehicleSuccess()
    {
        try {
            User user = new User();
            user.setVehicleModel("Hero");
            user.setVehicleType(2);
            user.setVehicleModel("spl");
            user.setVehicleCompany("hero");
            user.setVehicleNumber("TN08AP8800");
            user.setVehicleYear(2002);
            user.setId(4);


            Assertions.assertTrue(UserDao.addVehicle(user));

        }catch (DaoException e){
            e.printStackTrace();

        }
    }

}
