package com.fssa.reparo.validation;

import com.fssa.reparo.dao.WorkShopDao;
import com.fssa.reparo.exception.DAOException;
import com.fssa.reparo.exception.InvalidEntryException;
import com.fssa.reparo.exception.ValidationException;
import com.fssa.reparo.model.WorkShop;

public class WorkShopValidation {
    private final Validations  validate = new Validations();
   public boolean credentialValidate(WorkShop work) throws InvalidEntryException {



       return validate.stringValidation(work.getName())&&validate.numberValidation(work.getNumber())&&validate.passWordValidation(work.getPassword())&&validate.addressValidation(work.getAddress())&&validate.stringValidation(work.getCity())&&validate.stringValidation(work.getState())&&validate.workshopType(work.getType());
   }
   public boolean isValidWorkshop(WorkShop work) throws ValidationException {
       try{
       if(credentialValidate(work)){
           WorkShopDao workDao = new WorkShopDao();
           WorkShop chkWork = workDao.findWorkShopByNumber(work.getNumber());
           if(chkWork.getName() != null) throw new ValidationException("WorkShop already present");
               return  true;
           

       }}catch (InvalidEntryException | DAOException e){
          throw new ValidationException(e);
       }
       return false;
   }
   public int getWorkShop(long num , String pass) throws ValidationException{
           try {
               if(!(validate.loginCredentialValidation(num,pass))) throw new InvalidEntryException("invalid credentials") ;
               WorkShopDao workDao =  new WorkShopDao() ;
               WorkShop work = workDao.findWorkShopByNumber(num);
               if(work.getNumber() == num ){
                   if(work.getPassword().equals(pass)){
                      if(workDao.updateLoginStatus(work.getId(),true))
                       return work.getId();
                   }
                   else{
                       throw  new InvalidEntryException("invalid Password");
                   }

               }
               else{
                   throw  new InvalidEntryException("workshop not present");
               }

           } catch (DAOException | InvalidEntryException e) {
               throw new ValidationException(e);
           }
       return  0 ;

       }
       public boolean isWorkshopId(int id) throws ValidationException{
       WorkShopDao workDao = new WorkShopDao();
           try {
               WorkShop work = workDao.getWorkShopsById(id);
               return work.getName()!=null;
           } catch (DAOException e) {
               throw new ValidationException(e);
           }


       }








}
