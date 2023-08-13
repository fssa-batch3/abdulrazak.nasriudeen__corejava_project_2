package service;
import exception.DAOException;
import exception.ValidationException;
import model.User;
import validation.UserValidation;
import validation.Validations;

import  dao.UserDao;


public class UserServices {

    public static void registerUser(User user) throws ValidationException {

        Validations.rejectIfStringNullOrEmpty(user.getName());
        Validations.rejectIfStringNullOrEmpty(user.getPassword());

        UserValidation validate = new UserValidation();
        if(validate.validNewUser(user)){
            try {
                UserDao use = new UserDao();
                boolean chk  = use.insertUser(user);
                if(chk)System.out.println("User registered successfully");



            }catch (DAOException e){
                e.printStackTrace();
            }

        }

    }
    public static void loginUser(Long num , String pass){
        UserValidation validate = new UserValidation();
        validate.isUser(num,pass);

    }





}
