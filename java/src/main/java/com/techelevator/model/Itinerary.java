package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Itinerary {

    //TODO: add list of landmarks to itinerary properties, then fix mapper in Jdbc class

    @JsonProperty("id")
    private int itineraryId;
    @JsonProperty("user_id")
    private int userId;
    @JsonProperty("hotel")
    private Hotel hotel;

    private List<Landmark> landmarks = new ArrayList<>();

    public Itinerary(){
    };

    public List<Landmark> getLandmarks() {
        return landmarks;
    }

    public void addLandmark(Landmark landmark){
        landmarks.add(landmark);
    }

    public void setLandmarks(List<Landmark> landmarks) {
        this.landmarks = landmarks;
    }

    public int getItineraryId() {
        return itineraryId;
    }

    public void setItineraryId(int itineraryId) {
        this.itineraryId = itineraryId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

}
