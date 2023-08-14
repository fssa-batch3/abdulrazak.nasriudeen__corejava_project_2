package service;
import exception.ServiceException;
import model.WorkShop;
import org.junit.jupiter.api.Test;

public class WorkShopServiceTest {
    @Test
    void registerWorkShopTestFail(){
        WorkShopService dao = new WorkShopService();
        WorkShop use =  new WorkShop() ;
        use.setName("razak Test");
        use.setNumber(9840326580L);
        use.setPassword("abd234");
        use.setType(3);
        use.setState("tamil Nadu");
        use.setCity("chennai");
        use.setAddress("123  Main Street");
        try {
            dao.registerWorkShop(use);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

    }
}
