package com.fssa.reparo.model;

public class Booking extends BookingEntities {

    public  Booking (){

    }
    public Booking(int veh_id,String problem,String address,String city,String state ,boolean req_sts){
        this.setProblem(problem);
        this.setCity(city);
        this.setAddress(address);
        this.setState(state);
        this.setVehicleId(veh_id);
        this.setRequestStatus(req_sts);

    }
    public Booking (Vehicle vehicle){
        this.setVehicle(vehicle);
    }
    public Booking (WorkShop workShop){
        this.setWorkShop(workShop);
    }




}
