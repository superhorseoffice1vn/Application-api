package com.example.be.dto.request.employee;

public class UpdateEmployeeDto {
    private String name;
    private String phoneNumber;
    private String username;
    private String password;


    public UpdateEmployeeDto(final String name, final String phoneNumber, final String username, final String password) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(final String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }
}
