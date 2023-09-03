package com.fssa.reparo.service;

import com.fssa.reparo.dao.ServiceDao;
import com.fssa.reparo.datamapper.BookingMapper;
import com.fssa.reparo.datamapper.ServiceMapper;
import com.fssa.reparo.dto.booking.BookingResponseInclAcceptDto;
import com.fssa.reparo.dto.service.ServiceListResponseDto;
import com.fssa.reparo.dto.service.ServiceResponseDto;
import com.fssa.reparo.exception.DAOException;
import com.fssa.reparo.exception.InvalidEntryException;
import com.fssa.reparo.exception.ServiceException;
import com.fssa.reparo.exception.ValidationException;
import com.fssa.reparo.model.*;
import com.fssa.reparo.validation.BookingValidation;

import java.util.ArrayList;
import java.util.List;

public class ServiceListServices extends EachService{
    private final ServiceDao serviceDao = new ServiceDao();

    public boolean createServiceList(int bookingId) throws ServiceException {
        BookingValidation validation =  new BookingValidation();
        try {
            validation.isBookingId(bookingId);
           return serviceDao.createServiceList(bookingId);

        } catch (ValidationException | DAOException e) {
            throw new ServiceException(e);
        }


    }
public ServiceListResponseDto getServiceByBookingId(int id) throws ServiceException {
    // Create instances of necessary classes and variables
    BookingValidation validation = new BookingValidation();
    BookingServices bookingServ =  new BookingServices();
    ServiceListResponseDto responseDto = null;
    ServiceMapper map = new ServiceMapper();

    try {
        // Validate the booking ID using the BookingValidation class
        validation.isBookingId(id);
        Services service = serviceDao.getServiceListByBookingId(id);
        service.setServices(serviceDao.getServicesFromListId(service.getServiceListId()));
        int totalAmount = serviceDao.getTotalAmount(service.getServices());
        updateServiceAmount(service.getServiceListId(), totalAmount);
        service.setServiceAmount(totalAmount);
        BookingResponseInclAcceptDto bookResp =  bookingServ.getAcceptedLiveBookingById(service.getBookingId());
        responseDto = map.mapServicesToServiceListResponseDto(service,bookResp);
    } catch (ValidationException | DAOException e) {
        // Handle exceptions by wrapping them in a ServiceException and throwing it
        throw new ServiceException(e);
    }

    // Return the ServiceListResponseDto
    return responseDto;
}

    public ServiceListResponseDto getServiceById(int id) throws ServiceException {
        // Create instances of necessary classes and variables
        BookingValidation validation = new BookingValidation();
        ServiceListResponseDto responseDto ;
        BookingServices bookingServ =  new BookingServices();
        ServiceMapper map = new ServiceMapper();

        try {
            validation.isServiceId(id);
            Services service = serviceDao.getServiceListById(id);
            service.setServices(serviceDao.getServicesFromListId(id));
            int totalAmount = serviceDao.getTotalAmount(service.getServices());
            updateServiceAmount(service.getServiceListId(), totalAmount);
            service.setServiceAmount(totalAmount);
            BookingResponseInclAcceptDto bookResp =  bookingServ.getAcceptedLiveBookingById(service.getBookingId());
            responseDto = map.mapServicesToServiceListResponseDto(service,bookResp);
        } catch (ValidationException | DAOException e) {
            // Handle exceptions by wrapping them in a ServiceException and throwing it
            throw new ServiceException(e);
        }

        // Return the ServiceListResponseDto
        return responseDto;
    }
    public boolean updateServiceAmount(int serviceListId , int amount) throws ServiceException {
        BookingValidation validation =  new BookingValidation();
        try {
            validation.isServiceId(serviceListId);
            validation.priceValidation(amount);
            return serviceDao.updateServiceAmount(serviceListId,amount);
        } catch (ValidationException | DAOException | InvalidEntryException e) {
            throw new ServiceException(e);
        }
    }
    public boolean updateCancelService(int serviceListId , boolean cancel , String reason ) throws ServiceException {
        BookingValidation validation =  new BookingValidation();
        try {
            validation.isServiceId(serviceListId);

            return serviceDao.updateCancelStatus(serviceListId,cancel,reason);
        } catch (ValidationException | DAOException  e) {
            throw new ServiceException(e);
        }
    }
    public boolean updateAcceptService(int serviceListId , boolean accept  ) throws ServiceException {
        BookingValidation validation =  new BookingValidation();
        try {
            validation.isServiceId(serviceListId);
            return serviceDao.updateAcceptStatus(serviceListId,accept);
        } catch (ValidationException | DAOException  e) {
            throw new ServiceException(e);
        }
    }

    public List<ServiceListResponseDto> getAllServiceLists() throws ServiceException {
        List<ServiceListResponseDto> listResponse = new ArrayList<>();
        BookingServices bookService = new BookingServices();
        ServiceMapper map = new ServiceMapper();
        try{
            List<Integer> servicesList =  serviceDao.getAllServicelist();
            for( int id : servicesList){
                ServiceListResponseDto serviceList = getServiceById(id);
                listResponse.add(serviceList);
            }
        }catch (Exception e){
            throw new ServiceException(e);
        }
        return listResponse ;
    }

}
class EachService{
    public  boolean addService(ServiceList service) throws ServiceException {
        BookingValidation validation =  new BookingValidation();
        ServiceDao dao =  new ServiceDao();
        try {
            validation.serviceCredentialValidation(service);
            return dao.createService(service);
        } catch (ValidationException | DAOException e) {
            throw new ServiceException(e);
        }

    }
    public  boolean updateServiceDetail(ServiceList service) throws ServiceException {
        BookingValidation validation =  new BookingValidation();
        ServiceDao dao =  new ServiceDao();
        try {
            validation.serviceCredentialValidation(service);
            return dao.updateServiceDetails(service);
        } catch (ValidationException | DAOException e) {
            throw new ServiceException(e);
        }

    }
    public  boolean deleteEachService(int serviceId) throws ServiceException {
        BookingValidation validation =  new BookingValidation();
        ServiceDao dao =  new ServiceDao();
        try {
            validation.isServiceId(serviceId);
            return dao.deleteEachService(serviceId);
        } catch (ValidationException | DAOException e) {
            throw new ServiceException(e);
        }

    }






}