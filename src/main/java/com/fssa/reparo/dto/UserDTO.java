package com.fssa.reparo.dto;

public class UserDTO {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String name;
    private long number ;
    private String password;

    public String getPassword() {
        return password;
    }
  public UserDTO(){

  }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setPassword(String pass){
        this.password = pass;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }




    public UserDTO( String name, long number, String password){
        this.name = name;
        this.number = number;
        this.password = password;
    }

    public UserDTO( int id ,String name, long number, String password){
        this.name = name;
        this.number = number;
        this.password = password;
        this.id = id ;
    }
}
