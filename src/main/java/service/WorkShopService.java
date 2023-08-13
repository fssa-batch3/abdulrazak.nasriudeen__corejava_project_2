package service;
import dao.WorkShopDao;
import exception.DaoException;
import model.WorkShop;
import validation.InvalidEntryException;
import validation.WorkShopValidation;




public class WorkShopService {
    public static void registerWorkShop(WorkShop user){
        WorkShopValidation validate = new WorkShopValidation();
        if(validate.isValidWorkshop(user)){
            try {
                WorkShopDao work =  new WorkShopDao() ;
                boolean chk  = work.insertWorkShop(user);
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
            WorkShopDao workDao =  new WorkShopDao() ;
            WorkShop work = workDao.findWorkShopByNumber(num);
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
