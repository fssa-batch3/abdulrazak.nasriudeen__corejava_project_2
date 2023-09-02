package com.fssa.reparo.dto.vehicle;
import com.fssa.reparo.dto.user.UserResponseDto;

public class VehicleResponseDto extends VehicleDTO{
    private UserResponseDto userInfo;

    private VehicleRequestDto vehicleInfo;




    public VehicleResponseDto(UserResponseDto userInfo, VehicleRequestDto vehicleInfo,int vehicleId) {
        super(vehicleId);
        this.userInfo = userInfo;
        this.vehicleInfo = vehicleInfo;
    }


    public VehicleRequestDto getVehicleInfo() {
        return vehicleInfo;
    }

    public void setVehicleInfo(VehicleRequestDto vehicleInfo) {
        this.vehicleInfo = vehicleInfo;
    }



    public UserResponseDto getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserResponseDto userInfo) {
        this.userInfo = userInfo;
    }


}