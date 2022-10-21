package com.example.EmployeeRegistration.Dto;

import lombok.Data;

@Data
public class EmployeeDto {
    private int id;
    private String name;
    private String email;
    private String address;
    private String gender;
    private String designation;
    private String password;
}
