package com.fssa.reparo.dto;

public class WorkShopDTO
    {
        private int workshopId;
        private String workshopName;
        private int workshopType;
        private  long workshopNumber;

        private String workshopPassword;
        private String workshopAddress;
        private String city;
        public String getCity() {
        return city;
    }

        public void setCity(String city) {
        this.city = city;
    }

        public String getState() {
        return state;
    }

        public void setState(String state) {
        this.state = state;
    }

        private String state;


        public void setWorkshopId(int workshopId) {
        this.workshopId = workshopId;
    }

        public void setWorkshopPassword(String workshopPassword) {
        this.workshopPassword = workshopPassword;
    }

        public void setWorkshopNumber(long workshopNumber) {
        this.workshopNumber = workshopNumber;
    }

        public void setWorkshopName(String workshopName) {
        this.workshopName = workshopName;
    }

        public void setWorkshopType(int workshopType) {
        this.workshopType = workshopType;
    }

        public void setWorkshopAddress(String workshopAddress) {
        this.workshopAddress = workshopAddress;
    }


        public String getWorkshopName() {
        return workshopName;
    }

        public int getWorkshopId() {
        return workshopId;
    }

        public String getWorkshopAddress() {
        return workshopAddress;
    }

        public String getWorkshopPassword() {
        return workshopPassword;
    }

        public int getWorkshopType() {
        return workshopType;
    }


        public long getWorkshopNumber() {
        return workshopNumber;
    }
    }



