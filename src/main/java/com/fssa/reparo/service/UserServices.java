package com.fssa.reparo.service;
import com.fssa.reparo.exception.DAOException;
import com.fssa.reparo.exception.ServiceException;
import com.fssa.reparo.exception.ValidationException;
import com.fssa.reparo.model.User;
import com.fssa.reparo.validation.UserValidation;
import com.fssa.reparo.dao.UserDao;
import java.util.List;

public class UserServices {



    /**
     * Registers a new user by validating the input and inserting the user into the database.
     *
     * @param user The User object to be registered.
     * @return True if the user is successfully registered, false otherwise.
     * @throws ServiceException If there is an issue with database access or validation.
     */
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



    /**
     * Logs in a user by validating the credentials.
     *
     * @param num The user's identification number.
     * @param pass The user's password.
     * @return The result of the login attempt:
     *         - Positive user ID if login is successful.
     *         - Negative values for various login failure cases, e.g.:
     *           -1 if validation fails.
     * @throws ServiceException If there is an issue with validation.
     */

    public int loginUser(Long num , String pass) throws  ServiceException{
        UserValidation validate = new UserValidation();
        try {
           return validate.isUser(num,pass);
        } catch (ValidationException e) {
            throw new ServiceException(e);
        }


    }

    /**
     * Retrieves a user based on the specified user ID.
     *
     * @param id The ID of the user to be retrieved.
     * @return A User object representing the user with the specified ID.
     * @throws ServiceException If there is an issue with accessing the database.
     */
    public User getUserById(int id) throws  ServiceException{
        UserDao use = new UserDao();

        try {
            return use.findUserById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
    /**
     * Retrieves a list of all users stored in the database.
     *
     * @return A List of User objects representing all users in the database.
     * @throws ServiceException If there is an issue with accessing the database.
     */
    public List<User> getAllUsers() throws ServiceException {
        UserDao use = new UserDao();
        try {
           return use.getAllUser();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

    }








}
