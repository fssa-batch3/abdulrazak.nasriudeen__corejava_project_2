package validation;
import model.User;
import model.Vehicle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserValidationTest {
    @Test
    void userCredentialTestValidate(){
        User user =  new User();
        UserValidation useValidate =  new UserValidation();
        user.setName("Abdul Raza");
        user.setNumber(8124311602L);
        user.setPassword("abd123");
        try {
            Assertions.assertTrue(useValidate.userCredentialValidate(user));
        }catch (InvalidEntryException e){
            e.printStackTrace();
        }
    }
    @Test
    void invalidUserCredentialTestValidate(){
        User user =  new User();
        UserValidation useValidate =  new UserValidation();
        user.setName("Abdul1aza");
        user.setNumber(8124311602L);
        user.setPassword("abdulre");
        try {
            Assertions.assertFalse(useValidate.userCredentialValidate(user));
        }catch (InvalidEntryException e){
            e.printStackTrace();
        }
    }
    @Test
    void validUserVehicleTest(){
        Vehicle use =  new Vehicle();
        use.setVehicleNumber("AP05BC0004");
        use.setVehicleType(2);
        use.setVehicleCompany("Hero");
        use.setVehicleYear(2000);
        use.setVehicleModel("Splendor");
        UserValidation useValidate =  new UserValidation();
        try {
            Assertions.assertTrue(useValidate.userValidVehicle(use));
        }catch (InvalidEntryException e){
            e.printStackTrace();
        }


    }
    @Test
    void InvalidUserVehicleTest(){
        Vehicle use =  new Vehicle();
        use.setVehicleNumber("AP0BC0004");
        use.setVehicleType(1);
        use.setVehicleCompany("Hero");
        use.setVehicleYear(2027);
        use.setVehicleModel("Splendor");
        UserValidation useValidate =  new UserValidation();
        try {
            Assertions.assertFalse(useValidate.userValidVehicle(use));
        }catch (InvalidEntryException e){
            e.printStackTrace();
        }
    }




}
