package com.fssa.reparo.datamapper;

import com.fssa.reparo.dto.UserDTO;
import com.fssa.reparo.model.User;

public class UserMapper {
    public UserDTO mapUserToDTO(User user){
        UserDTO dto = new UserDTO();
        dto.setName(user.getName());
        dto.setNumber(user.getNumber());
        dto.setPassword(user.getPassword());
        return dto;
    }
    public User mapDTOToUser(UserDTO dto){
        User user =  new User();
        user.setId(dto.getId());
        user.setName(dto.getName());
        user.setNumber(dto.getNumber());
        user.setPassword(dto.getPassword());
        return user;
    }

}
