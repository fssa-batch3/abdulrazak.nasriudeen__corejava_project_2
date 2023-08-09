package service;
import exception.DaoException;
import model.User;
import validation.UserValidation;
import static dao.UserDao.insertUser;


public class UserServices {

    public static void registerUser(User user){
        UserValidation validate = new UserValidation();
        if(validate.validNewUser(user)){
            try {
                boolean chk  = insertUser(user);
                if(chk)System.out.println("User registered successfully");



            }catch (DaoException e){
                e.printStackTrace();
            }

        }

    }
    public static void loginUser(Long num , String pass){
        UserValidation validate = new UserValidation();
        validate.isUser(num,pass);

    }





}
