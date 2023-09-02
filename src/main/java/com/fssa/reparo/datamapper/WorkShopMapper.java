package com.fssa.reparo.datamapper;
import com.fssa.reparo.dto.workshop.WorkShopRequestDto;
import com.fssa.reparo.dto.workshop.WorkShopResponseDto;
import com.fssa.reparo.model.WorkShop;

public class WorkShopMapper {
    public WorkShop mapRequestDtoToWorkshop(WorkShopRequestDto dto){
        WorkShop workShop = new WorkShop();
        workShop.setName(dto.getWorkshopName());
        workShop.setNumber(dto.getWorkshopNumber());
        workShop.setPassword(dto.getWorkshopPassword());
        workShop.setAddress(dto.getWorkshopAddress());
        workShop.setCity(dto.getCity());
        workShop.setState(dto.getState());
        workShop.setType(dto.getWorkshopType());
        return workShop;
    }
    public WorkShopResponseDto mapWorkShopToResponseDto(WorkShop workShop){

        return new WorkShopResponseDto(workShop.getId(), workShop.getName(), workShop.getNumber(), workShop.getType(), workShop.getAddress(), workShop.getCity(), workShop.getState());
    }

}
