package com.fssa.reparo.datamapper;
import com.fssa.reparo.dto.booking.BookingResponseInclAcceptDto;
import com.fssa.reparo.dto.service.ServiceListResponseDto;
import com.fssa.reparo.dto.service.ServiceRequestDto;
import com.fssa.reparo.dto.service.ServiceResponseDto;
import com.fssa.reparo.model.ServiceList;
import com.fssa.reparo.model.Services;
import java.util.ArrayList;
import java.util.List;


public class ServiceMapper {
    // Maps a ServiceRequestDto to a ServiceList
    public ServiceList mapRequestDtoToServiceList(ServiceRequestDto dto){
        ServiceList service = new ServiceList();
        service.setServiceListId(dto.getServiceListId());
        service.setServiceName(dto.getServiceName());
        service.setPrice(dto.getServicePrice());
        return service;
    }

    // Maps a ServiceList to a ServiceResponseDto
    public ServiceResponseDto mapServiceListToResponseDto(ServiceList service){
        ServiceRequestDto requestDto =  new ServiceRequestDto(service.getServiceListId(), service.getPrice(), service.getServiceName());
        return new ServiceResponseDto(service.getServiceId(), requestDto);
    }

    // Maps a Services object to a ServiceListResponseDto
    public ServiceListResponseDto mapServicesToServiceListResponseDto(Services services ,BookingResponseInclAcceptDto bookingDto ){
        List<ServiceList> listOfServices = services.getServices();
        List<ServiceResponseDto> listOfServiceResponse =  new ArrayList<>();

        // Map each ServiceList to a ServiceResponseDto
        for (ServiceList ser : listOfServices){
            listOfServiceResponse.add(mapServiceListToResponseDto(ser));
        }

        // Map the Booking object to a BookingResponseInclAcceptDto


        // Create and return a ServiceListResponseDto
        return new ServiceListResponseDto(
                listOfServiceResponse,
                bookingDto,
                services.getServiceListId(),
                services.getServiceAmount(),
                services.isAcceptStatus(),
                services.isCancelStatus(),
                services.getCancelReason()
        );
    }
}

