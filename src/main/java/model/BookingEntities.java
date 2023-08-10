package model;

public abstract class BookingEntities {
    private int bookingId;
    private  boolean requestStatus;
    private boolean AcceptStatus;
    private int workShopId;
    private int vehicleId;
    private String problem;
    private String address ;
    public int getWorkShopId() {
        return workShopId;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public void setWorkShopId(int workShopId) {
        this.workShopId = workShopId;
    }



    public boolean isAcceptStatus() {
        return AcceptStatus;
    }

    public void setAcceptStatus(boolean acceptStatus) {
        AcceptStatus = acceptStatus;
    }

    public boolean isRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(boolean requestStatus) {
        this.requestStatus = requestStatus;
    }

    private String city;
    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getProblem() {
        return problem;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }
    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }
}
