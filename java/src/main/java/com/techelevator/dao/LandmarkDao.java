package com.techelevator.dao;

import com.techelevator.model.Landmark;

import java.util.List;

public interface LandmarkDao {

    List<Landmark> listLandmarks();

    Landmark getLandmark(int landmarkId);

    boolean createLandmark(Landmark landmark, int addressId);

    Landmark updateLandmarkLikes(Landmark landmark, int landmarkId);

    Landmark updatePendingStatus(Landmark landmark);

    boolean deleteLandmark(int landmarkId);
}
