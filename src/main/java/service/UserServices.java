package service;
import exception.DaoException;
import model.User;
import validation.UserValidation;

import static dao.UserDao.findUserByNumber;
import static dao.UserDao.insertUser;
import static dao.UserDao.addVehicle;

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
    public static void userVehicle(User us,long num,String pass){
        try {
            UserValidation validate = new UserValidation();
            if(validate.isUser(num,pass)){
                User use = findUserByNumber(num);
                use.setVehicleType(us.getVehicleType());
                use.setVehicleNumber(us.getVehicleNumber());
                use.setVehicleCompany(us.getVehicleCompany());
                use.setVehicleModel(us.getVehicleModel());
                use.setVehicleYear(us.getVehicleYear());

                if(addVehicle(use))System.out.println("your vehicle is added successfully");
                else System.out.println("Your vehicle haven't added");


            }


        }catch (DaoException e){
            e.printStackTrace();
        }
    }
    public static void main(String []args){
        User user = new User();
       user.setVehicleType(2);
       user.setVehicleModel("spl");
       user.setVehicleCompany("hero");
       user.setVehicleNumber("tniwodj2io");
       user.setVehicleYear(2002);

       registerUser(user);
        loginUser(8124311602L,"abdul123");
        userVehicle(user,8124311602L,"abdul123");
    }



}
