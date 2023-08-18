package com.fssa.reparo.model;


public class User extends UserEntities{

    public  User (){

    }
    public User(String name , long num , String pass){
        this.setName(name);
        this.setNumber(num);
        this.setPassword(pass);

    }

}


