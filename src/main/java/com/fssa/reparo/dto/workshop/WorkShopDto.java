package com.fssa.reparo.dto.workshop;

public class WorkShopDto {
    private  int workshopId ;

    public WorkShopDto(int id) {
        this.workshopId = id;
    }
    public WorkShopDto() {

    }

    public int getWorkshopId() {
        return workshopId;
    }

    public void setWorkshopId(int id) {
        this.workshopId = id;
    }
}
