package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Review {

    @JsonProperty("id")
    private int reviewId;
    @JsonProperty("landmark_id")
    private int landmarkId;
    @JsonProperty("user_id")
    private int userId;
    @JsonProperty("description")
    private String description;
    @JsonProperty("title")
    private String title;
    @JsonProperty("is_liked")
    private boolean isLiked;
    @JsonProperty("username")
    private String username;

    public Review(){};


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean getIsLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        this.isLiked = liked;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public int getLandmarkId() {
        return landmarkId;
    }

    public void setLandmarkId(int landmarkId) {
        this.landmarkId = landmarkId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
