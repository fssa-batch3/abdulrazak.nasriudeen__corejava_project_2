package com.fssa.reparo.dto.user;

public class UserRequestDto extends UserResponseDto {


    private String password;

    public UserRequestDto( String name, long number, String password) {
        super(name,number);

        this.password =  password;
    }




    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }




}
