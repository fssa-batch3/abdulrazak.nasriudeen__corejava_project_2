package com.fssa.reparo.dto.workshop;

public class WorkShopResponseDto extends WorkShopDto {
    private String workshopName;
    private int workshopType;
    private  long workshopNumber;
    private String workshopAddress;
    private String city;
    private String state;

    public WorkShopResponseDto(int id ,String workshopName ,long workshopNumber,int workshopType,String workshopAddress,String city,String state ) {
        super(id);
        this.workshopName = workshopName;
        this.workshopType = workshopType;
        this.workshopNumber = workshopNumber;
        this.workshopAddress = workshopAddress;
        this.city = city;
        this.state = state;
    }
    public WorkShopResponseDto(String workshopName ,long workshopNumber,int workshopType,String workshopAddress,String city,String state ) {
        super();
        this.workshopName = workshopName;
        this.workshopType = workshopType;
        this.workshopNumber = workshopNumber;
        this.workshopAddress = workshopAddress;
        this.city = city;
        this.state = state;
    }



    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getWorkshopAddress() {
        return workshopAddress;
    }

    public void setWorkshopAddress(String workshopAddress) {
        this.workshopAddress = workshopAddress;
    }
    public long getWorkshopNumber() {
        return workshopNumber;
    }

    public void setWorkshopNumber(long workshopNumber) {
        this.workshopNumber = workshopNumber;
    }

    public int getWorkshopType() {
        return workshopType;
    }

    public void setWorkshopType(int workshopType) {
        this.workshopType = workshopType;
    }

    public String getWorkshopName() {
        return workshopName;
    }

    public void setWorkshopName(String workshopName) {
        this.workshopName = workshopName;
    }
}
