package com.fssa.reparo.validation;
import com.fssa.reparo.dao.VehicleDAO;
import com.fssa.reparo.exception.DAOException;
import com.fssa.reparo.exception.InvalidEntryException;
import com.fssa.reparo.exception.ValidationException;
import com.fssa.reparo.model.User;
import com.fssa.reparo.dao.UserDAO;
import com.fssa.reparo.model.Vehicle;
import java.util.Objects;

public class UserValidation {
    protected UserDAO userDao  =  new UserDAO();
    private final Validations validate = new Validations();
    public boolean userCredentialValidate(User user) throws InvalidEntryException {

    return validate.stringValidation(user.getName()) && validate.numberValidation(user.getNumber())&&validate.passWordValidation(user.getPassword());
    }
    public boolean userCredentialValidateLogin(long num , String pass) throws InvalidEntryException {

        return validate.numberValidation(num)&&validate.passWordValidation(pass);
    }
    public boolean userValidVehicle(Vehicle u)throws InvalidEntryException{


        return validate.stringValidation(u.getVehicleCompany())&&validate.stringValidation(u.getVehicleModel())&&validate.vehicleNumberValidation(u.getVehicleNumber())&&validate.vehicleYearValidation(u.getVehicleYear())&&validate.workshopType(u.getVehicleType());

    }
    public boolean validNewUser(User user) throws  ValidationException {
        try {
            if (!userCredentialValidate(user)) throw new ValidationException("user Credentials is not valid");
            User chkUser = userDao.findUserByNumber(user.getNumber());
            if (chkUser.getName() != null) throw new ValidationException("User already present");
            return chkUser.getName() == null;


        } catch (InvalidEntryException | DAOException e) {
            throw new ValidationException(e);
        }

    }public int isUser(long num , String pass) throws ValidationException {
        try {
            if (!validate.loginCredentialValidation(num,pass)) throw new ValidationException("invalid Credentials");
            User chkUser = userDao.findUserByNumber(num);
            if(chkUser.getName() == null) throw new ValidationException("user not present ");
            if(!Objects.equals(chkUser.getPassword(), pass)) throw new ValidationException("password is incorrect");
            userDao.updateLoginStatus(chkUser.getId(), true);
            return chkUser.getId();
        }
        catch (InvalidEntryException | DAOException e){
            throw   new ValidationException(e);
        }


    }
    public boolean validVehicleId(int id) throws ValidationException{
        try {
            VehicleDAO vehicleDao =  new VehicleDAO();
            Vehicle vehicle =  vehicleDao.findVehicleById(id);
            if(vehicle.getVehicleNumber()==null)throw new ValidationException("vehicle not present");


            return  vehicle.getVehicleNumber()!=null;
        } catch (DAOException e) {
            throw new ValidationException(e);
        }
    }
    public boolean validUserId(int id) throws ValidationException{
        try {
            User user =  userDao.findUserById(id);
            if(user.getName()==null)throw new ValidationException("user not present");
            return user.getName()!=null;
        } catch (DAOException e) {
            throw new ValidationException("User not Present");
        }
    }
    public boolean userIsLogin(int id) throws ValidationException{
        try {
            if(validUserId(id)){
                User user =  userDao.findUserById(id);
                return user.isLogin();
            }
            return false;

        } catch (DAOException | ValidationException e) {
            throw new ValidationException(e);
        }
    }

}
