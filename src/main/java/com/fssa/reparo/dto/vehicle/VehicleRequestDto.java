package com.fssa.reparo.dto.vehicle;


import com.fssa.reparo.dto.user.UserDTO;

public class VehicleRequestDto extends UserDTO {
    private String company;
    private String model;
    private String  vehicleNumber;
    private int type;
    private int year;
    public VehicleRequestDto(int userId,String company , String model, String vehicleNumber,int type,int year ) {
        super(userId);
        this.company = company;
        this.model =  model;
        this.vehicleNumber =  vehicleNumber;
        this.type = type ;
        this.year =  year;
    }
    public VehicleRequestDto(String company , String model, String vehicleNumber,int type,int year ) {
        this.company = company;
        this.model =  model;
        this.vehicleNumber =  vehicleNumber;
        this.type = type ;
        this.year =  year;
    }
    public VehicleRequestDto(){
        super();

    }
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getCompany() {
        return company;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public void setCompany(String company) {
        this.company = company;
    }


}
