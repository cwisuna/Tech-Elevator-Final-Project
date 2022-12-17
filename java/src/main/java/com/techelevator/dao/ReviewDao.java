package com.techelevator.dao;

import com.techelevator.model.Review;

import java.security.Principal;
import java.util.List;

public interface ReviewDao {

    List<Review> listReviews(int landmarkId);
    List<Review> listAllReviews();

//    Review getReview(int id);

    Review createReview(Review review, int landmarkId, Principal principal);
}
