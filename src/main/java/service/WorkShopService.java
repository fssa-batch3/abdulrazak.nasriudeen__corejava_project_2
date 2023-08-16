package service;
import dao.WorkShopDao;
import exception.DAOException;
import exception.InvalidEntryException;
import exception.ServiceException;
import exception.ValidationException;
import model.WorkShop;
import validation.Validations;
import validation.WorkShopValidation;
import java.util.ArrayList;
public class WorkShopService {
    public  void registerWorkShop(WorkShop user)throws ServiceException{
        WorkShopValidation validate = new WorkShopValidation();
        if(validate.isValidWorkshop(user)){
            try {
                WorkShopDao work =  new WorkShopDao() ;
                boolean chk  = work.insertWorkShop(user);
                if(chk)System.out.println("User registered successfully");
                else System.out.println("User not registered successfully");



            }catch (DAOException e){
               throw  new ServiceException(e) ;
            }

        }else{
            throw  new ServiceException("invalid credentials ");
        }


    }
    public int loginWorkShop(long num,String pass) throws ServiceException {
        WorkShopValidation validate = new WorkShopValidation();
        try {
           return validate.getWorkShop(num , pass);
        } catch (ValidationException e) {
            throw new ServiceException(e);
        }
    }
    public ArrayList<WorkShop> getAllWorkShop() throws ServiceException{
        WorkShopDao work  = new WorkShopDao();
        try {
            return work.getAllWorkShops();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

    }
    public ArrayList<Integer> getWorkShopByArea(String city) throws ServiceException{
        Validations validate =  new Validations();
        WorkShopDao dao =  new WorkShopDao() ;
        ArrayList<Integer> workShop  =  new ArrayList<>();
        try {
            if(validate.stringValidation(city)){
                workShop =  dao.findWorkshopsByArea(city);
            }
        } catch (InvalidEntryException | DAOException e) {
            throw new ServiceException(e);
        }
        return workShop ;
    }
    public WorkShop getWorkShopById(int id) throws ServiceException{
        WorkShopDao dao =  new WorkShopDao() ;
        try {
            return dao.getWorkShopsById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }


    }
    public ArrayList<Integer> getWorkShopByType(int type) throws ServiceException {
        Validations validate = new Validations();
        ArrayList<Integer> arr = new ArrayList<>();
        WorkShopDao dao = new WorkShopDao();
        try {

            if (validate.WorkshopType(type)) {
                arr = dao.getWorkshopsByType(type);
            }
            return arr ;
        }catch (DAOException e){
            throw new ServiceException(e);
        }

    }



}
