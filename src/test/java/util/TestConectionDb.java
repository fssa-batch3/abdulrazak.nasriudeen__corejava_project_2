package util;
import exception.DTBException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

public class TestConectionDb {

    @Test
    void testConnection(){
        boolean chk = false;
        try {
            Connection connect = ConnectionDb.getConnection();
            chk =  true;
        }catch (DTBException e){

            chk =  false;


        }
        Assertions.assertTrue(chk,"DataBase is Connected");


    }
}
