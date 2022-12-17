package com.techelevator.controller;

import com.techelevator.dao.LandmarkDao;
import com.techelevator.dao.ReviewDao;
import com.techelevator.model.Landmark;
import com.techelevator.model.Review;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
public class ReviewController {

    private ReviewDao reviewDao;

    public ReviewController(ReviewDao reviewDao) {
        this.reviewDao = reviewDao;
    }

    @RequestMapping(path="/review/list/{id}", method= RequestMethod.GET)
    public List<Review> getAllReviews(@Valid @PathVariable("id") int landmarkId){

        return reviewDao.listReviews(landmarkId);
    }

//    @RequestMapping(path="/review/{id}", method= RequestMethod.GET)
//    public Review getReviews(@Valid @PathVariable("id") int reviewId){
//        Review result = reviewDao.getReview(reviewId);
//        if(result == null){
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Review not found", null);
//        }
//        return result;
//    }


    @RequestMapping(path= "/review/new/{landmarkId}", method= RequestMethod.POST)
    public Review newReview(@Valid @RequestBody Review review, @PathVariable("landmarkId") int landmarkId, Principal principal){
//        boolean success = reviewDao.createReview(review);
//        if(!success){
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Review to be added was not found", null);
//        }

        return reviewDao.createReview(review, landmarkId, principal);
    }


}
