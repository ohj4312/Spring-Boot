package com.example.response.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ObjectUser {
    private String name;
    private int age;

    @JsonProperty("phone_number")
    private String phoneNumber;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public ObjectUser(){
        this.name=null;
        this.age=0;
        this.phoneNumber=null;
    }
    public ObjectUser(String name, int age,String phoneNumber){
        this.name=name;
        this.age=age;
        this.phoneNumber=phoneNumber;
    }

//    public ObjectUser getDefaultUser(){
//        return new ObjectUser("deafult",0);
//    }


    public ObjectUser getDefaultUser(){
        return new ObjectUser("deafult",0,"010-1111-2222");
    }
    @Override
    public String toString() {
        return "ObjectUser{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
