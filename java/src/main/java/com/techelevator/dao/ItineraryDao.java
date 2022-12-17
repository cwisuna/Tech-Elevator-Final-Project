package com.techelevator.dao;

import com.techelevator.model.Itinerary;
import com.techelevator.model.Landmark;

import java.security.Principal;
import java.util.List;

public interface ItineraryDao {

    List<Itinerary> listItinerary(Principal principal);

    Itinerary getItinerary(int itineraryId, Principal principal);

    int createItinerary(int hotelId, Itinerary itinerary, Principal principal);

    void insertItineraryIntoAssociative(int itineraryId, List<Landmark> landmarks);

    void deleteItinerary(int itineraryId);

    void addLandmarkToItinerary(Itinerary itinerary, int landmarkId);

    void removeLandmarkToItinerary(Landmark landmark, int itineraryId);

    void deleteItineraryFromAssociative(int itineraryId);

}
