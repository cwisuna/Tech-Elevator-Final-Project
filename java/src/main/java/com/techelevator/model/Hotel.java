package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.techelevator.dao.AddressDao;
import com.techelevator.dao.JdbcAddressDao;

public class Hotel {

    @JsonProperty("id")
    private int hotelId;
    @JsonProperty("address_id")
    private int addressId;
    @JsonProperty("name")
    private String name;

    private Address address;

    public Hotel(){};

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    public Address getAddress() {
        return this.address;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
