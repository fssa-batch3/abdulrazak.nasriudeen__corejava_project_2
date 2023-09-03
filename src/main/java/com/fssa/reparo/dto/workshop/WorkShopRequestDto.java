package com.fssa.reparo.dto.workshop;

public class WorkShopRequestDto  extends  WorkShopResponseDto{
    private String workshopPassword;
    public WorkShopRequestDto(String workshopName, long workshopNumber, String workshopPassword, int workshopType, String workshopAddress, String city, String state) {
        super(workshopName, workshopNumber, workshopType, workshopAddress, city, state);
        this.workshopPassword = workshopPassword;
    }
    public WorkShopRequestDto() {
    	super();
    }
    public String getWorkshopPassword() {
        return workshopPassword;
    }
    public void setWorkshopPassword(String workshopPassword) {
        this.workshopPassword = workshopPassword;
    }
}
