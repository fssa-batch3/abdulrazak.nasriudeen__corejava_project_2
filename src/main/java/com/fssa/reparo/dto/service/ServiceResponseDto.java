package com.fssa.reparo.dto.service;

public class ServiceResponseDto  {
    private int serviceId;
    private ServiceRequestDto serviceInfo;

    public ServiceResponseDto(int serviceId , ServiceRequestDto serviceInfo) {
        this.serviceId = serviceId;
        this.serviceInfo = serviceInfo;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public ServiceRequestDto getServiceInfo() {
        return serviceInfo;
    }

    public void setServiceInfo(ServiceRequestDto serviceInfo) {
        this.serviceInfo = serviceInfo;
    }
}
