package com.fssa.reparo.model;

public class Booking extends BookingEntities {

    public  Booking (){

    }
    public Booking(int vehId,String problem,String address,String city,String state ,boolean reqSts){
        this.setProblem(problem);
        this.setCity(city);
        this.setAddress(address);
        this.setState(state);
        this.setVehicleId(vehId);
        this.setRequestStatus(reqSts);

    }

    public Booking (Vehicle vehicle){
        this.setVehicle(vehicle);
    }
    public Booking (WorkShop workShop){
        this.setWorkShop(workShop);
    }





}
