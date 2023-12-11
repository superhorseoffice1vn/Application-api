package com.example.be.dto.response.employee;

public class EmployeeDetailDto {
    private Integer id;
    private String name;
    private String phoneNumber;
    private String username;

    public EmployeeDetailDto() {
    }

    public EmployeeDetailDto(final Integer id, final String name, final String phoneNumber, final String username) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
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
}
