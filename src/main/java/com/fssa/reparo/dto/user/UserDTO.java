package com.fssa.reparo.dto.user;

public class UserDTO {
    private int id;

    public UserDTO(int id) {
        this.id =  id;
    }
    public UserDTO() {
    }

    public int getUserId() {
        return id;
    }

    public void setUserId(int vehicleId) {
        this.id = vehicleId;
    }

}
