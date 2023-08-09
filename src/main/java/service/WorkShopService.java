package service;

import exception.DaoException;
import model.User;
import model.WorkShop;
import validation.InvalidEntryException;
import validation.UserValidation;
import validation.WorkShopValidation;

import static dao.WorkShopDao.findWorkShopByNumber;
import static dao.WorkShopDao.insertWorkShop;


public class WorkShopService {
    public static void registerWorkShop(WorkShop user){
        WorkShopValidation validate = new WorkShopValidation();
        if(validate.isValidWorkshop(user)){
            try {
                boolean chk  = insertWorkShop(user);
                if(chk)System.out.println("User registered successfully");
                else System.out.println("User not registered successfully");



            }catch (DaoException e){
                e.printStackTrace();
            }

        }else{
            System.out.println("enter valid credentials");
        }


    }
    public static void loginWorkShop(long num,String pass){
        WorkShopValidation validate = new WorkShopValidation();
        try{
        if(validate.isLogin(num,pass)){
            WorkShop work = findWorkShopByNumber(num);
            if(work.getNumber() == num){
            if(work.getPassword().equals(pass)){
                System.out.println(work.getName()+" Succesfully logged in ");
            }else{
                System.out.println("Incorrect PassWord");
            }}else{
                System.out.println("User not present");

            }


        }else System.out.println("Enter valid credentials");
        }catch (InvalidEntryException | DaoException e){
            e.printStackTrace();
        }

    }
    
}
