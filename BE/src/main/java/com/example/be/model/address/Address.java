package com.example.be.model.address;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import java.util.Set;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String detailAddress;
    private String town;
    private String district;
    private String city;
    private String country;

    @OneToMany(mappedBy = "address")
    @JsonBackReference
    private Set<UserAddress> userAddresses;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(final String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public String getTown() {
        return town;
    }

    public void setTown(final String town) {
        this.town = town;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(final String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(final String country) {
        this.country = country;
    }

    public Set<UserAddress> getUserAddresses() {
        return userAddresses;
    }

    public void setUserAddresses(final Set<UserAddress> userAddresses) {
        this.userAddresses = userAddresses;
    }
}
