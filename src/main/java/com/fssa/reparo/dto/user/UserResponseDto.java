package com.fssa.reparo.dto.user;

public class UserResponseDto extends UserDTO {

    private String name;
    private long number ;


    public UserResponseDto(int id, String name, long number) {
        super(id);
        this.name = name;
        this.number = number;
    }
    public UserResponseDto( String name, long number) {
        super();
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }


}



