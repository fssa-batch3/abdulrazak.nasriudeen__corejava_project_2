package com.fssa.reparo.service;
import com.fssa.reparo.dao.WorkShopDao;
import com.fssa.reparo.exception.DAOException;
import com.fssa.reparo.exception.InvalidEntryException;
import com.fssa.reparo.exception.ServiceException;
import com.fssa.reparo.exception.ValidationException;
import com.fssa.reparo.model.WorkShop;
import com.fssa.reparo.validation.Validations;
import com.fssa.reparo.validation.WorkShopValidation;
import java.util.ArrayList;
import java.util.List;

public class WorkShopService {
    public  void registerWorkShop(WorkShop user)throws ServiceException{
        WorkShopValidation validate = new WorkShopValidation();

            try {
                if(validate.isValidWorkshop(user)) {
                    WorkShopDao work = new WorkShopDao();
                    boolean chk = work.insertWorkShop(user);
                    if (chk) System.out.println("User registered successfully");
                    else System.out.println("User not registered successfully");
                }



            }catch (DAOException | ValidationException e){
               throw  new ServiceException(e) ;
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
    public List<WorkShop> getAllWorkShop() throws ServiceException{
        WorkShopDao work  = new WorkShopDao();
        try {
            return work.getAllWorkShops();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

    }
    public List<Integer> getWorkShopByArea(String city) throws ServiceException{
        Validations validate =  new Validations();
        WorkShopDao dao =  new WorkShopDao() ;
        List<Integer> workShop  =  new ArrayList<>();
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
    public List<Integer> getWorkShopByType(int type) throws ServiceException {
        Validations validate = new Validations();
        List<Integer> arr = new ArrayList<>();
        WorkShopDao dao = new WorkShopDao();
        try {

            if (validate.workshopType(type)) {
                arr = dao.getWorkshopsByType(type);
            }
            return arr ;
        }catch (DAOException e){
            throw new ServiceException(e);
        }

    }



}
