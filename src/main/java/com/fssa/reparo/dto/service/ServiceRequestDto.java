package com.fssa.reparo.dto.service;

public class ServiceRequestDto {
    private int serviceListId;
    private int servicePrice;
    private String serviceName;

    public ServiceRequestDto(int serviceListId, int servicePrice, String serviceName) {
        this.serviceListId = serviceListId;
        this.servicePrice = servicePrice;
        this.serviceName = serviceName;
    }





    public int getServiceListId() {
        return serviceListId;
    }

    public void setServiceListId(int serviceListId) {
        this.serviceListId = serviceListId;
    }

    public int getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(int servicePrice) {
        this.servicePrice = servicePrice;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}

