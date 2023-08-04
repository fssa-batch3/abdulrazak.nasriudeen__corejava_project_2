package model;

public abstract class UserEntities extends VehicleEntities {
    private int id;
    private String name;
    private long number;
    private String password;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return this.name;
    }


    public long getNumber() {
        return number;
    }

    public String getPassword() {
        return password;
    }

    public String toString() {
        return "UserEntities{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number=" + number +
                ", password='" + password + '\'' +
                '}';
    }
}
abstract class VehicleEntities{
    private String vehicleCompany;
    private String vehicleNumber ;

    private int vehicleType;
    private int user_id;
    private int vehicleId;
    private int vehicleYear;

    public int getVehicleYear() {
        return vehicleYear;
    }

    public void setVehicleYear(int vehicleYear) {
        this.vehicleYear = vehicleYear;
    }

    private  String vehicleModel;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public int getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(int vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public String getVehicleCompany() {
        return vehicleCompany;
    }

    public void setVehicleCompany(String vehicleCompany) {
        this.vehicleCompany = vehicleCompany;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }




}
