package model;

public class Booking extends BookingEntities {

    public  Booking (){

    }
    public Booking (Vehicle vehicle){
        this.setVehicle(vehicle);
    }
    public Booking (WorkShop workShop){
        this.setWorkShop(workShop);
    }




}
