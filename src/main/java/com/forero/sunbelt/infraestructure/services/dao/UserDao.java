package com.forero.sunbelt.infraestructure.services.dao;

import lombok.Data;

@Data
public class UserDao {
    private String firstName;
    private String secondName;
    private String surname;
    private String secondSurname;
    private String phone;
    private String address;
    private String city;

    public UserDao(String firstName, String secondName, String surname, String secondSurname, String phone, String address, String city) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.surname = surname;
        this.secondSurname = secondSurname;
        this.phone = phone;
        this.address = address;
        this.city = city;
    }
}