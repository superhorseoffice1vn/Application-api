package com.example.be.dto.request.agent;

public class UpdateAgentDto {

    private String nameAgent;
    private String nameUser;
    private String phoneNumber;
    private String address;
    private String locationGoogleMap;
    private Integer idUser;

    public String getNameAgent() {
        return nameAgent;
    }

    public void setNameAgent(final String nameAgent) {
        this.nameAgent = nameAgent;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(final String nameUser) {
        this.nameUser = nameUser;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(final String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public String getLocationGoogleMap() {
        return locationGoogleMap;
    }

    public void setLocationGoogleMap(final String locationGoogleMap) {
        this.locationGoogleMap = locationGoogleMap;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(final Integer idUser) {
        this.idUser = idUser;
    }
}
