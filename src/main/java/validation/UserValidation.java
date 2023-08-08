package validation;
import exception.DaoException;
import model.Bookings;
import model.User;
import dao.UserDao;

public class UserValidation {
    public boolean userCredentialValidate(User user) throws InvalidEntryException {
        Validations validate = new Validations();
    return validate.stringValidation(user.getName()) && validate.numberValidation(user.getNumber())&&validate.passWordValidation(user.getPassword());
    }
    public boolean userCredentialValidateLogin(long num , String pass) throws InvalidEntryException {
        Validations validate = new Validations();
        return validate.numberValidation(num)&&validate.passWordValidation(pass);
    }
    public boolean userValidVehicle(User u)throws InvalidEntryException{
        Validations validate = new Validations();

        return validate.stringValidation(u.getVehicleCompany())&&validate.stringValidation(u.getVehicleModel())&&validate.vehicleNumberValidation(u.getVehicleNumber())&&validate.vehicleYearValidation(u.getVehicleYear())&&validate.WorkshopType(u.getVehicleType());

    }
    public boolean bookingValidation(Bookings book)throws InvalidEntryException{
        Validations validate =  new  Validations();
        return validate.stringValidation(book.getProblem())&&validate.stringValidation(book.getAddress())&&validate.stringValidation(book.getCity())&&validate.stringValidation(book.getState());
    }

    public boolean validNewUser(User user){
        try {
            if (userCredentialValidate(user)){
                User chkUser = UserDao.findUserByNumber(user.getNumber());
                if(chkUser.getName() == null ){
                    return true;
                }else {
                    System.out.println("Sorry user already present");
                    return false;
                }


            }else{
                System.out.println("Enter a valid User Credentials ");
            }
        }catch (InvalidEntryException | DaoException e){
            e.printStackTrace();
        }
        return false;
    }
    public boolean isUser(long num , String pass){
        try {
            if (userCredentialValidateLogin(num,pass)){
                User chkUser = UserDao.findUserByNumber(num);
                if(chkUser.getName() != null ){
                    if(chkUser.getPassword().equals(pass)){
                    System.out.print(chkUser.getName() + "Success fully logged in");}
                    else{
                        System.out.println("Invalid Pa ssword");
                    }
                    return true;
                }else {
                    System.out.println("Sorry,mobile number is not found ");
                    return false;
                }


            }else{
                System.out.println("Enter a valid User Credentials ");
            }
        }catch (InvalidEntryException | DaoException e){
            e.printStackTrace();
        }
        return false;

    }

}
