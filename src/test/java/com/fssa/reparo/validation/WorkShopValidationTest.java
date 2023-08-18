package com.fssa.reparo.validation;

import com.fssa.reparo.validation.WorkShopValidation;
import com.fssa.reparo.exception.InvalidEntryException;
import com.fssa.reparo.model.WorkShop;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WorkShopValidationTest {

    @Test
    void validWorkShop(){
        WorkShop work = new WorkShop();
        work.setAddress("123  Main Street");
        work.setType(2);
        work.setCity("chennai");
        work.setState("Tamil nadu");
        work.setName("auto mobiles ");
        work.setNumber(8124311703L);
        work.setPassword("auto123");
        WorkShopValidation tst = new WorkShopValidation();
        try {
           Assertions.assertTrue(tst.credentialValidate(work));
        }catch (InvalidEntryException e){
            e.printStackTrace();
        }



    }
    @Test
    void InvalidWorkShop(){
        WorkShop work = new WorkShop();
        work.setAddress("3/401,5th main road , kodungaiyur");
        work.setType(2);
        work.setCity("chennai");
        work.setState("Tamil nadu");
        work.setName("auto mobiles ");
        work.setNumber(8124311703L);
        work.setPassword("auto123");
        WorkShopValidation tst = new WorkShopValidation();
        try {
            Assertions.assertFalse(tst.credentialValidate(work));
        }catch (InvalidEntryException e){
            e.printStackTrace();
        }



    }


}
