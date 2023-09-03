package com.fssa.reparo.dto.vehicle;
import com.fssa.reparo.dto.user.UserResponseDto;

public class VehicleResponseDto extends VehicleDTO{
    private UserResponseDto userInfo;

    private VehicleRequestDto vehicle;




    public VehicleResponseDto(UserResponseDto userInfo, VehicleRequestDto vehicleInfo,int vehicleId) {
        super(vehicleId);
        this.userInfo = userInfo;
        this.vehicle = vehicleInfo;
    }


    public VehicleRequestDto getVehicleInfo() {
        return vehicle;
    }

    public void setVehicleInfo(VehicleRequestDto vehicleInfo) {
        this.vehicle = vehicleInfo;
    }



    public UserResponseDto getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserResponseDto userInfo) {
        this.userInfo = userInfo;
    }


}