package service;
import exception.DAOException;
import exception.ServiceException;
import exception.ValidationException;
import model.User;
import validation.UserValidation;
import  dao.UserDao;
import java.util.ArrayList;
public class UserServices {

    public void registerUser(User user) throws ServiceException {
        UserValidation validate = new UserValidation();
        if(validate.validNewUser(user)){
            try {
                UserDao use = new UserDao();
                boolean chk  = use.insertUser(user);
                if(chk)System.out.println("User registered successfully");



            }catch (DAOException e){
                throw new ServiceException(e);
            }

        }
    }
    public int loginUser(Long num , String pass) throws  ServiceException{
        UserValidation validate = new UserValidation();
        try {
           return validate.isUser(num,pass);
        } catch (ValidationException e) {
            throw new ServiceException(e);
        }


    }
    public User getUserById(int id) throws  ServiceException{
        UserDao use = new UserDao();

        try {
            return use.findUserById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
    public ArrayList <User> getAllUsers() throws ServiceException {
        UserDao use = new UserDao();
        try {
           return use.getAllUser();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

    }







}
