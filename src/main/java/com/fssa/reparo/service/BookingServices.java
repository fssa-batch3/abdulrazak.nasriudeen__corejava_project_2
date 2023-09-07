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
    // DAO and validation objects for managing bookings
    protected BookingDao bookingDao = new BookingDao();
    protected BookingValidation bookingValidation = new BookingValidation();
    protected WorkShopValidation workshopValidate = new WorkShopValidation();

    // Create a new booking based on a request DTO
    public int createBooking(BookingRequestDto request) throws ServiceException {
        // Map the request DTO to a Booking object
        BookingMapper map = new BookingMapper();
        Booking booking = map.mapRequestDtoToBooking(request);

        // Set initial status values
        booking.setLive(true);
        booking.setRequestStatus(true);
        booking.setAcceptStatus(false);

        try {
            // Validate the booking
            bookingValidation.validBooking(booking);

            // Insert the booking into the database
            bookingDao.insertBooking(booking);

            // Return the generated booking ID
            return bookingDao.getBookingIdByVehicleId(booking.getVehicleId());
        } catch (ValidationException | DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    // Update the request status of a booking
    public void updateRequestStatus(boolean status, int id) throws ServiceException {
        try {
            // Validate the booking ID
            bookingValidation.isBookingId(id);

            // Update the request status in the database
            bookingDao.updateRequestSts(id, status);
        } catch (DAOException | ValidationException e) {
            throw new ServiceException(e);
        }
    }

    // Update the accept status of a booking for a specific workshop
    public void updateAcceptStatus(boolean status, int workShopId, int bookingId) throws ServiceException {
        try {
            // Validate the booking ID and workshop ID
            bookingValidation.isBookingId(bookingId);
            workshopValidate.isWorkshopId(workShopId);

            // Update the accept status in the database
            bookingDao.updateAcceptSts(bookingId, workShopId, status);
        } catch (DAOException | ValidationException e) {
            throw new ServiceException(e);
        }
    }

    // Get a booking by its ID
    public BookingResponseDto getBookingById(int id) throws ServiceException {
        try {
            // Map the booking to a response DTO
            BookingMapper map = new BookingMapper();
            Booking booking;
            bookingValidation.isBookingId(id);
            booking = bookingDao.getBookingById(id);
            return map.mapBookingToResponseDto(booking);
        } catch (ValidationException | DAOException e) {
            throw new ServiceException(e);
        }
    }

    // Get a booking by vehicle ID
    public BookingResponseDto getBookingByVehicleId(int id) throws ServiceException {
        try {
            // Map the booking to a response DTO
            BookingMapper map = new BookingMapper();
            Booking booking;
            bookingValidation.isBookingVehicleId(id);
            booking = bookingDao.getBookingByVehicleId(id);
            return map.mapBookingToResponseDto(booking);
        } catch (ValidationException | DAOException e) {
            throw new ServiceException(e);
        }
    }

    // Get a list of all bookings
    public List<Booking> getAllBookings() throws ServiceException {
        try {
            return bookingDao.getAllBookings();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    // Find bookings by a specified area
    public List<Integer> findBookingByArea(String area) throws ServiceException {
        List<Integer> bookings;
        try {
            // Validate the booking area
            bookingValidation.bookingCityValidation(area);

            // Find bookings near the specified area
            bookings = bookingDao.findBookingNearByCity(area);
        } catch (ValidationException | DAOException e) {
            throw new ServiceException(e);
        }
        return bookings;
    }

    // Get an unaccepted live booking by ID
    public BookingResponseExclAcceptDto getUnAcceptedLiveBookingById(int id) throws ServiceException {
        try {
            // Validate the booking ID
            bookingValidation.isBookingId(id);
            VehicleDao vehicleDao = new VehicleDao();
            BookingMapper map = new BookingMapper();
            Booking booking = bookingDao.findUnAcceptedLiveBookingById(id);

            // Check if a live booking is available
            if (booking.getProblem() == null) {
                throw new ServiceException("No live booking available");
            }

            // Map the booking and associated vehicle to a response DTO
            Vehicle vehicle = vehicleDao.findVehicleById(booking.getVehicleId());
            booking.setVehicle(vehicle);
            return map.mapBookingToResponseExclDto(booking);
        } catch (ValidationException | DAOException e) {
            throw new ServiceException(e);
        }
    }

    // Get a list of all unaccepted bookings
    public List<BookingResponseExclAcceptDto> getAllUnAcceptedBooking() throws ServiceException {
        List<BookingResponseExclAcceptDto> bookingsResponse = new ArrayList<>();
        BookingMapper map = new BookingMapper();
        UserDao userDAO = new UserDao();
        try {
            // Get all unaccepted bookings and map them to response DTOs
            List<Booking> bookings = bookingDao.getAllUnAcceptedBooking();
            for (Booking book : bookings) {
                Vehicle vehicle = book.getVehicle();
                vehicle.setUser(userDAO.findUserById(vehicle.getUserId()));
                book.setVehicle(vehicle);
                bookingsResponse.add(map.mapBookingToResponseExclDto(book));
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return bookingsResponse;
    }

    // Get an accepted live booking by ID
    public BookingResponseInclAcceptDto getAcceptedLiveBookingById(int id) throws ServiceException {
        try {
            // Validate the booking ID
            bookingValidation.isBookingId(id);
            UserDao userdao = new UserDao();
            BookingMapper map = new BookingMapper();
            Booking booking = bookingDao.findAcceptedLiveBookingById(id);

            // Map the booking and associated user to a response DTO
            Vehicle vehicle = booking.getVehicle();
            vehicle.setUser(userdao.findUserById(vehicle.getUserId()));
            booking.setVehicle(vehicle);
            return map.mapBookingToResponseInclDto(booking);
        } catch (ValidationException | DAOException e) {
            throw new ServiceException(e);
        }
    }

    // Get a list of all accepted bookings
    public List<BookingResponseInclAcceptDto> getAllAcceptedBooking() throws ServiceException {
        List<BookingResponseInclAcceptDto> bookingsResponse = new ArrayList<>();
        BookingMapper map = new BookingMapper();
        UserDao userDAO = new UserDao();
        try {
            // Get all accepted bookings and map them to response DTOs
            List<Booking> bookings = bookingDao.getAllAcceptedBooking();
            for (Booking book : bookings) {
                Vehicle vehicle = book.getVehicle();
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
