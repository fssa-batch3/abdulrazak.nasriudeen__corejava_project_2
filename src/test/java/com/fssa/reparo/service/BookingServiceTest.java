package com.fssa.reparo.service;
import com.fssa.reparo.exception.ServiceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BookingServiceTest {
    protected  BookingServices bookService =  new BookingServices();

     @Test
    void updateRequestTest(){
         try {
             Assertions.assertTrue(bookService.updateRequestStatus(true,18));
         } catch (ServiceException e) {
             throw new RuntimeException(e);
         }
     }
    @Test
    void updateRequestTestFail(){
        try {
            Assertions.assertFalse(bookService.updateRequestStatus(true,0));
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }
}
