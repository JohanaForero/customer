package com.forero.sunbelt.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private String firstName;
    private String secondName;
    private String surname;
    private String secondSurname;
    private String phone;
    private String address;
    private String city;
    private String documentType;
    private String documentNumber;
}
