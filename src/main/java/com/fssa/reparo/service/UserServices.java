package com.fssa.reparo.service;
import com.fssa.reparo.exception.DAOException;
import com.fssa.reparo.exception.InvalidEntryException;
import com.fssa.reparo.exception.ServiceException;
import com.fssa.reparo.exception.ValidationException;
import com.fssa.reparo.model.User;
import com.fssa.reparo.validation.UserValidation;
import com.fssa.reparo.dao.UserDao;
import java.util.List;

public class UserServices {
    private final UserDao userDao=  new UserDao();
    private final UserValidation userValidation=  new UserValidation();




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

            if(validate.validNewUser(user)){
                return userDao.insertUser(user);
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


        try {
            return userDao.findUserById(id);
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

        try {
           return userDao.getAllUser();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

    }
    /**
     * updates the password of the user with provided phone number.
     *
     * @param number The phone number of the user to be found.
     * @return true if the password is updated else false if it occurs any issues.
     * @throws ServiceException If there are issues while querying the database.
     */
    public boolean updateUserPassword(String newPassword , long number) throws ServiceException{


        try {
            if(userValidation.userCredentialValidateLogin(number, newPassword)) {
                User user = userDao.findUserByNumber(number);
                if (userValidation.validUserId(user.getId())) {
                    return userDao.updateUserPassword(user.getId(), newPassword);
                }
            }


          return false;
        } catch (DAOException | ValidationException | InvalidEntryException e) {
            throw new ServiceException(e);
        }


    }

    /**
     * updates the password of the user with provided phone number.
     *
     * @param id The id of the user to be found.
     * @return true if the password is updated else false if it occurs any issues.
     * @throws ServiceException If there are issues while querying the database.
     */
    public boolean logOutUser(int id) throws ServiceException{
        try {
           if(userValidation.userIsLogin(id)){
               return userDao.updateLoginStatus(id,false);
           }
            return false;
        } catch (ValidationException |DAOException e) {
            // If any exception Occurs throws as Service Exception.
            throw new ServiceException(e);
        }

    }








}
