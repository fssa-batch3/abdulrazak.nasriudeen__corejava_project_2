package com.fssa.reparo.datamapper;
import com.fssa.reparo.dto.WorkShopDTO;
import com.fssa.reparo.model.WorkShop;

public class WorkShopMapper {

    public WorkShopDTO mapWorkShopToDTO(WorkShop workShop){
        WorkShopDTO dto = new WorkShopDTO();
        dto.setWorkshopId(workShop.getId());
        dto.setWorkshopName(workShop.getName());
        dto.setWorkshopNumber(workShop.getNumber());
        dto.setWorkshopPassword(workShop.getPassword());
        dto.setWorkshopType(workShop.getType());
        dto.setWorkshopAddress(workShop.getAddress());
        dto.setCity(workShop.getCity());
        dto.setState(workShop.getState());
        return dto;
    }
    public WorkShop mapDTOToWorkShop(WorkShopDTO dto){
        WorkShop workShop =  new WorkShop();
        workShop.setId(dto.getWorkshopId());
        workShop.setName(dto.getWorkshopName());
        workShop.setNumber(dto.getWorkshopNumber());
        workShop.setPassword(dto.getWorkshopPassword());
        workShop.setType(dto.getWorkshopType());
        workShop.setAddress(dto.getWorkshopAddress());
        workShop.setCity(dto.getCity());
        workShop.setState(dto.getState());

        return workShop;
    }

}
