package com.fssa.reparo.validation;
import com.fssa.reparo.exception.DAOException;
import com.fssa.reparo.exception.InvalidEntryException;
import com.fssa.reparo.exception.ValidationException;
import com.fssa.reparo.model.Booking;
import com.fssa.reparo.model.User;
import com.fssa.reparo.dao.UserDao;
import com.fssa.reparo.model.Vehicle;

public class UserValidation {
    public boolean userCredentialValidate(User user) throws InvalidEntryException {
        Validations validate = new Validations();
    return validate.stringValidation(user.getName()) && validate.numberValidation(user.getNumber())&&validate.passWordValidation(user.getPassword());
    }
    public boolean userCredentialValidateLogin(long num , String pass) throws InvalidEntryException {
        Validations validate = new Validations();
        return validate.numberValidation(num)&&validate.passWordValidation(pass);
    }
    public boolean userValidVehicle(Vehicle u)throws InvalidEntryException{
        Validations validate = new Validations();

        return validate.stringValidation(u.getVehicleCompany())&&validate.stringValidation(u.getVehicleModel())&&validate.vehicleNumberValidation(u.getVehicleNumber())&&validate.vehicleYearValidation(u.getVehicleYear())&&validate.workshopType(u.getVehicleType());

    }
    public boolean validBooking(Booking book)throws InvalidEntryException{
        Validations validate =  new  Validations();
        return validate.stringValidation(book.getProblem())&&validate.addressValidation(book.getAddress())&&validate.stringValidation(book.getCity())&&validate.stringValidation(book.getState());
    }

    public boolean validNewUser(User user) throws  ValidationException{
        try {
            if (userCredentialValidate(user)){
                UserDao userDao = new UserDao();
                User chkUser = userDao.findUserByNumber(user.getNumber());
                return chkUser.getName() == null;


            }
        }catch (InvalidEntryException | DAOException e){
            throw   new ValidationException(e);
        }
        return false;
    }
    public int isUser(long num , String pass) throws ValidationException {
        try {
            if (userCredentialValidateLogin(num,pass)){
                UserDao userDao = new UserDao();
                User chkUser = userDao.findUserByNumber(num);
                if(chkUser.getName() != null ){
                    if(chkUser.getPassword().equals(pass)){
                    return chkUser.getId();
                    }
                    else{
                        throw new InvalidEntryException("Password is incorrect");
                    }

                }else {
                    throw new InvalidEntryException("User is not present");
                }


            }

        }
        catch (InvalidEntryException | DAOException e){
            throw   new ValidationException(e);
        }
        return 0;// invalid credentials

    }

}
