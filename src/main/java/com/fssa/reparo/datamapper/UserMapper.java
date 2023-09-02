package com.fssa.reparo.datamapper;

import com.fssa.reparo.dto.user.UserDTO;
import com.fssa.reparo.dto.user.UserRequestDto;
import com.fssa.reparo.dto.user.UserResponseDto;
import com.fssa.reparo.model.User;

public class UserMapper {
    /**
     * Maps a UserRequestDto to a User object.
     *
     * @param dto The UserRequestDto to be mapped.
     * @return A User object with the mapped values.
     */
    public User mapRequestDtoToUser(UserRequestDto dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setNumber(dto.getNumber());
        user.setPassword(dto.getPassword());
        return user;
    }
    /**
     * Maps a User object to a UserResponseDto.
     *
     * @param user The User object to be mapped.
     * @return A UserResponseDto with the mapped values.
     */
    public UserResponseDto mapUserToResponseDto(User user) {
        return new UserResponseDto(user.getId(), user.getName(), user.getNumber());
    }




}
