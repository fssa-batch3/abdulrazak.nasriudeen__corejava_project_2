package validation;

import dao.WorkShopDao;
import exception.DaoException;
import model.WorkShop;

public class WorkShopValidation {
   public boolean credentialValidate(WorkShop work) throws InvalidEntryException{
       Validations validate =  new Validations();


       return validate.stringValidation(work.getName())&&validate.numberValidation(work.getNumber())&&validate.passWordValidation(work.getPassword())&&validate.addressValidation(work.getAddress())&&validate.stringValidation(work.getCity())&&validate.stringValidation(work.getState())&&validate.WorkshopType(work.getType());
   }
   public boolean isValidWorkshop(WorkShop work){
       try{
       if(credentialValidate(work)){
           WorkShopDao workDao = new WorkShopDao();
           WorkShop chkWork = workDao.findWorkShopByNumber(work.getNumber());
           if(chkWork.getName() == null){
               return  true;
           }

       }}catch (InvalidEntryException | DaoException e){
           e.printStackTrace();
       }
       return false;
   }
   public boolean isLogin(long num , String pass) throws InvalidEntryException{
       Validations validate =  new Validations();
       return validate.numberValidation(num)&&validate.passWordValidation(pass);
   }


}
