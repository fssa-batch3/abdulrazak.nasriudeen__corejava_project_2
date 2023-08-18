package validation;

import dao.WorkShopDao;
import exception.DAOException;
import exception.InvalidEntryException;
import exception.ValidationException;
import model.WorkShop;

public class WorkShopValidation {
   public boolean credentialValidate(WorkShop work) throws InvalidEntryException {
       Validations validate =  new Validations();


       return validate.stringValidation(work.getName())&&validate.numberValidation(work.getNumber())&&validate.passWordValidation(work.getPassword())&&validate.addressValidation(work.getAddress())&&validate.stringValidation(work.getCity())&&validate.stringValidation(work.getState())&&validate.WorkshopType(work.getType());
   }
   public boolean isValidWorkshop(WorkShop work) throws ValidationException {
       try{
       if(credentialValidate(work)){
           WorkShopDao workDao = new WorkShopDao();
           WorkShop chkWork = workDao.findWorkShopByNumber(work.getNumber());
           if(chkWork.getName() == null){
               return  true;
           }

       }}catch (InvalidEntryException | DAOException e){
          throw new ValidationException(e);
       }
       return false;
   }
   public boolean isLogin(long num , String pass) throws InvalidEntryException{
       Validations validate =  new Validations();
       return validate.numberValidation(num)&&validate.passWordValidation(pass);
   }
   public int getWorkShop(long num , String pass) throws ValidationException{
           try {
               if(isLogin(num,pass)){
               WorkShopDao workDao =  new WorkShopDao() ;
               WorkShop work = workDao.findWorkShopByNumber(num);
               if(work.getNumber() ==  num ){
                   if(work.getPassword().equals(pass)){
                       return work.getId();
                   }
                   else{
                       throw  new InvalidEntryException("invalid Password");
                   }

               }
               else{
                   throw  new InvalidEntryException("invalid Number");
               }

           }} catch (DAOException | InvalidEntryException e) {
               throw new ValidationException(e);
           }
       return  0 ;

       }






}
