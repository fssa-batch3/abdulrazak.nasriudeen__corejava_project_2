package com.fssa.reparo.service;
import com.fssa.reparo.dao.BookingDao;
import com.fssa.reparo.dao.UserDao;
import com.fssa.reparo.dao.VehicleDao;
import com.fssa.reparo.datamapper.BookingMapper;
import com.fssa.reparo.dto.booking.BookingRequestDto;
import com.fssa.reparo.dto.booking.BookingResponseDto;
import com.fssa.reparo.dto.booking.BookingResponseExclAcceptDto;
import com.fssa.reparo.dto.booking.BookingResponseInclAcceptDto;
import com.fssa.reparo.exception.DAOException;
import com.fssa.reparo.exception.ServiceException;
import com.fssa.reparo.exception.ValidationException;
import com.fssa.reparo.model.Booking;
import com.fssa.reparo.model.Vehicle;
import com.fssa.reparo.validation.BookingValidation;

import com.fssa.reparo.validation.WorkShopValidation;

import java.util.ArrayList;
import java.util.List;
public class BookingServices {
    protected BookingDao bookingDao =  new BookingDao();
    protected  BookingValidation bookingValidation =  new BookingValidation();
    protected WorkShopValidation workshopValidate = new WorkShopValidation();


    public  int  createBooking(BookingRequestDto request) throws  ServiceException{
        BookingMapper map = new BookingMapper();
        Booking booking =  map.mapRequestDtoToBooking(request);
        booking.setLive(true);
        booking.setRequestStatus(true);
        booking.setAcceptStatus(false);
        try {
            bookingValidation.validBooking(booking);
            bookingDao.insertBooking(booking);
            return bookingDao.getBookingIdByVehicleId(booking.getVehicleId());

        } catch (ValidationException | DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }
    public void updateRequestStatus(boolean status , int id) throws ServiceException{

        try {
            bookingValidation.isBookingId(id) ;
            bookingDao.updateRequestSts(id,status);

        } catch (DAOException | ValidationException e) {
            throw new ServiceException(e);
        }
    }
    public void updateAcceptStatus(boolean status,int workShopId , int bookingId) throws ServiceException{
            try {

                bookingValidation.isBookingId(bookingId);
                workshopValidate.isWorkshopId(workShopId);
                bookingDao.updateAcceptSts(bookingId,workShopId,status);

            } catch (DAOException |ValidationException e) {
                throw new ServiceException(e);
            }
    }
    public BookingResponseDto getBookingById(int id)throws ServiceException{
        try {
            BookingMapper map =  new BookingMapper();
            Booking booking;
            bookingValidation.isBookingId(id);
            booking =  bookingDao.getBookingById(id);
            return map.mapBookingToResponseDto(booking);
        } catch (ValidationException |DAOException e) {
            throw new ServiceException(e);
        }

    }
    public BookingResponseDto getBookingByVehicleId(int id)throws ServiceException{
        try {
            BookingMapper map =  new BookingMapper();
            Booking booking;
            bookingValidation.isBookingVehicleId(id);
            booking =  bookingDao.getBookingByVehicleId(id);
            return map.mapBookingToResponseDto(booking);
        } catch (ValidationException |DAOException e) {
            throw new ServiceException(e);
        }

    }
    public List<Booking> getAllBookings() throws  ServiceException{
        try {
            return bookingDao.getAllBookings();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
    public List<Integer> findBookingByArea(String area) throws ServiceException{
        List<Integer> bookings;
        try {
            bookingValidation.bookingCityValidation(area);
           bookings =   bookingDao.findBookingNearByCity(area);

        } catch (ValidationException | DAOException e) {
            throw new ServiceException(e);
        }

        return bookings;


    }
    public BookingResponseExclAcceptDto getUnAcceptedLiveBookingById(int id) throws ServiceException {
        try {
            bookingValidation.isBookingId(id);
            VehicleDao vehicleDao =  new VehicleDao();

            BookingMapper map = new BookingMapper();
            Booking booking =  bookingDao.findUnAcceptedLiveBookingById(id);
            if(booking.getProblem()==null)throw new ServiceException("no Live Booking Available");
            Vehicle vehicle = vehicleDao.findVehicleById(booking.getVehicleId());
            booking.setVehicle(vehicle);
            return map.mapBookingToResponseExclDto(booking);
        } catch (ValidationException |DAOException e) {
            throw new ServiceException(e);
        }

    }
    public List<BookingResponseExclAcceptDto> getAllUnAcceptedBooking() throws ServiceException{
        List<BookingResponseExclAcceptDto> bookingsResponse = new ArrayList<>();
        BookingMapper map =  new BookingMapper();
        UserDao userDAO =  new UserDao();
        try {
           List<Booking> bookings = bookingDao.getAllUnAcceptedBooking();
           for(Booking book : bookings){
               Vehicle vehicle =  book.getVehicle();
               vehicle.setUser(userDAO.findUserById(vehicle.getUserId()));
               book.setVehicle(vehicle);
               bookingsResponse.add(map.mapBookingToResponseExclDto(book));
           }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return bookingsResponse;
    }
    public BookingResponseInclAcceptDto getAcceptedLiveBookingById(int id) throws ServiceException {
        try {
            bookingValidation.isBookingId(id);
            UserDao userdao = new UserDao();
            BookingMapper map = new BookingMapper();
            Booking booking =  bookingDao.findAcceptedLiveBookingById(id);
            Vehicle vehicle =  booking.getVehicle();
            vehicle.setUser(userdao.findUserById(vehicle.getUserId()));
            booking.setVehicle(vehicle);
            return map.mapBookingToResponseInclDto(booking);
        } catch (ValidationException |DAOException e) {
            throw new ServiceException(e);
        }

    }
    public List<BookingResponseInclAcceptDto> getAllAcceptedBooking() throws ServiceException{
        List<BookingResponseInclAcceptDto> bookingsResponse = new ArrayList<>();
        BookingMapper map =  new BookingMapper();
        UserDao userDAO =  new UserDao();
        try {
            List<Booking> bookings = bookingDao.getAllAcceptedBooking();
            for(Booking book : bookings){
                Vehicle vehicle =  book.getVehicle();
                vehicle.setUser(userDAO.findUserById(vehicle.getUserId()));
                book.setVehicle(vehicle);
                bookingsResponse.add(map.mapBookingToResponseInclDto(book));
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }


        return bookingsResponse;

    }



    }
