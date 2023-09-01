package com.fssa.reparo.datamapper;
import com.fssa.reparo.dto.WorkShopDTO;
import com.fssa.reparo.model.WorkShop;

public class WorkShopMapper {

    public WorkShopDTO mapWorkShopToDTO(WorkShop workShop){
        WorkShopDTO dto = new WorkShopDTO();
        dto.setId(workShop.getId());
        dto.setName(workShop.getName());
        dto.setNumber(workShop.getNumber());
        dto.setPassword(workShop.getPassword());
        dto.setType(workShop.getType());
        dto.setAddress(workShop.getAddress());
        dto.setCity(workShop.getCity());
        dto.setState(workShop.getState());
        return dto;
    }
    public WorkShop mapDTOToWorkShop(WorkShopDTO dto){
        WorkShop workShop =  new WorkShop();
        workShop.setId(dto.getId());
        workShop.setName(dto.getName());
        workShop.setNumber(dto.getNumber());
        workShop.setPassword(dto.getPassword());
        workShop.setType(dto.getType());
        workShop.setAddress(dto.getAddress());
        workShop.setCity(dto.getCity());
        workShop.setState(dto.getState());

        return workShop;
    }

}
