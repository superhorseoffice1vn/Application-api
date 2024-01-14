package com.example.be.model.Agent;

import com.example.be.model.user.User;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Agent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nameAgent;
    private String nameUser;
    private String phoneNumber;
    private String address;
    private String registrationDate;
    private String locationGoogleMap;
    private boolean deleteStatus;

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

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

    public boolean isDeleteStatus() {
        return deleteStatus;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(final String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public void setDeleteStatus(final boolean deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }
}
