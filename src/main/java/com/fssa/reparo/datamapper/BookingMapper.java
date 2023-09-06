package com.fssa.reparo.datamapper;

import com.fssa.reparo.dto.booking.BookingRequestDto;
import com.fssa.reparo.dto.booking.BookingResponseDto;
import com.fssa.reparo.dto.booking.BookingResponseExclAcceptDto;
import com.fssa.reparo.dto.booking.BookingResponseInclAcceptDto;
import com.fssa.reparo.dto.vehicle.VehicleResponseDto;
import com.fssa.reparo.dto.workshop.WorkShopResponseDto;
import com.fssa.reparo.model.Booking;


public class BookingMapper {
    /**
     * Maps a BookingRequestDto to a Booking object.
     *
     * @param dto The BookingRequestDto to be mapped.
     * @return A Booking object with the mapped values.
     */
    public Booking mapRequestDtoToBooking(BookingRequestDto dto){
        Booking booking =  new Booking();
        booking.setVehicleId(dto.getVehicleId());
        booking.setAddress(dto.getBookingAddress());
        booking.setCity(dto.getBookingCity());
        booking.setState(dto.getBookingState());
        booking.setProblem(dto.getProblem());
        return booking;

    }
    /**
     * Maps a Booking object to a BookingResponseExclAcceptDto.
     *
     * @param booking The Booking object to be mapped.
     * @return A BookingResponseExclAcceptDto with the mapped values.
     */
    public BookingResponseExclAcceptDto mapBookingToResponseExclDto(Booking booking) {
        // Create a VehicleMapper instance to map the Vehicle object
        VehicleMapper map = new VehicleMapper();
        BookingRequestDto bookingInfo = new BookingRequestDto();
        bookingInfo.setProblem(booking.getProblem());
        bookingInfo.setBookingAddress(booking.getAddress());
        bookingInfo.setBookingCity(booking.getCity());
        bookingInfo.setBookingState(booking.getState());
        bookingInfo.setRequestStatus(booking.isRequestStatus());
        bookingInfo.setAcceptStatus(booking.isAcceptStatus());

        // Map the Vehicle information
        VehicleResponseDto vehicleInfo = map.mapVehicleToResponseDto(booking.getVehicle());

        // Create a BookingRequestDto and set its properties from the Booking object


        // Create and return a new BookingResponseExclAcceptDto using the mapped values
        return new BookingResponseExclAcceptDto(booking.getBookingId(), bookingInfo, vehicleInfo);
    }

    /**
     * Maps a Booking object to a BookingResponseInclAcceptDto.
     *
     * @param booking The Booking object to be mapped.
     * @return A BookingResponseInclAcceptDto with the mapped values.
     */
    public BookingResponseInclAcceptDto mapBookingToResponseInclDto(Booking booking) {
        // Create a VehicleMapper instance to map the Vehicle object
        VehicleMapper vehicleMap = new VehicleMapper();

        UserMapper userMap  =  new UserMapper();

        // Create a WorkShopMapper instance to map the WorkShop object
        WorkShopMapper workShopMap = new WorkShopMapper();

        // Map the Vehicle information
        VehicleResponseDto vehicleInfo = vehicleMap.mapVehicleToResponseDto(booking.getVehicle());

        vehicleInfo.setUserInfo(userMap.mapUserToResponseDto(booking.getVehicle().getUser()));

        // Map the WorkShop information
        WorkShopResponseDto workShopInfo = workShopMap.mapWorkShopToResponseDto(booking.getWorkShop());

        // Create a BookingRequestDto and set its properties from the Booking object
        BookingRequestDto bookingInfo = new BookingRequestDto();
        bookingInfo.setProblem(booking.getProblem());
        bookingInfo.setBookingAddress(booking.getAddress());
        bookingInfo.setBookingCity(booking.getCity());
        bookingInfo.setBookingState(booking.getState());
        bookingInfo.setRequestStatus(booking.isRequestStatus());
        bookingInfo.setAcceptStatus(booking.isAcceptStatus());

        // Create and return a new BookingResponseInclAcceptDto using the mapped values
        return new BookingResponseInclAcceptDto(booking.getBookingId(), bookingInfo, vehicleInfo, workShopInfo);
    }

    public BookingResponseDto mapBookingToResponseDto(Booking booking){
        BookingRequestDto requestDto =  new BookingRequestDto();
        requestDto.setVehicleId(booking.getVehicleId());
        requestDto.setProblem(booking.getProblem());
        requestDto.setBookingState(booking.getState());
        requestDto.setBookingCity(booking.getCity());
        requestDto.setBookingAddress(booking.getAddress());

        return  new BookingResponseDto(booking.getBookingId(),requestDto);
    }



}
