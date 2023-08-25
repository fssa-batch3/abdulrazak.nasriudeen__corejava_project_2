package com.fssa.reparo.service;
import com.fssa.reparo.exception.DAOException;
import com.fssa.reparo.exception.ServiceException;
import com.fssa.reparo.exception.ValidationException;
import com.fssa.reparo.model.User;
import com.fssa.reparo.validation.UserValidation;
import com.fssa.reparo.dao.UserDao;
import java.util.List;

public class UserServices {
    public boolean registerUser(User user) throws ServiceException {
        UserValidation validate = new UserValidation();
        try {
            UserDao use = new UserDao();
            if(validate.validNewUser(user)){
                return use.insertUser(user);
            }

        }catch (DAOException | ValidationException e){
            throw new ServiceException(e);
        }

        return  false;
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
    public List<User> getAllUsers() throws ServiceException {
        UserDao use = new UserDao();
        try {
           return use.getAllUser();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

    }
    public boolean updateUserPassword(){

    }







}
