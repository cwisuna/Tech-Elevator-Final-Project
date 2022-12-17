package com.techelevator.controller;

import com.techelevator.dao.AddressDao;
import com.techelevator.dao.LandmarkDao;
import com.techelevator.model.Landmark;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
public class LandmarkController {

    private LandmarkDao landmarkDao;
    private AddressDao addressDao;

    public LandmarkController(LandmarkDao landmarkDao, AddressDao addressDao) {

        this.landmarkDao = landmarkDao;
        this.addressDao = addressDao;
    }

    @RequestMapping(path="/landmark/list", method= RequestMethod.GET)
    public List<Landmark> getAllLandmarks(){
        return landmarkDao.listLandmarks();
    }

    @RequestMapping(path="/landmark/{id}", method= RequestMethod.GET)
    public Landmark getLandmarks(@Valid @PathVariable("id") int landmarkId){
        Landmark result = landmarkDao.getLandmark(landmarkId);
        if(result == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Landmark not found", null);
        }
        return result;
    }

    //used to update likes count (int)
    @RequestMapping(path = "/landmark/{id}", method= RequestMethod.PUT)
    public Landmark updateLandmarkLikes(@Valid @RequestBody Landmark landmark, @PathVariable("id") int landmarkId){
        Landmark result = landmarkDao.updateLandmarkLikes(landmark, landmarkId);
        if(result == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Landmark not found", null);
        }
        return result;
    }

    @RequestMapping(path ="/pending/approve", method=RequestMethod.PUT)
    public Landmark approvePendingLandmark(@Valid @RequestBody Landmark landmark){
        Landmark result = landmarkDao.updatePendingStatus(landmark);
        if(result == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Landmark not found", null);
        }

        return result;
    }

    @RequestMapping(path= "/landmark/new", method= RequestMethod.POST)
    public void newLandmark(@Valid @RequestBody Landmark landmark){
        //call create address method and assign to int addressId
        int addressId = addressDao.createAddress(landmark.getAddress());

        //use addressId to insert into landmark table
        landmarkDao.createLandmark(landmark, addressId);

        //don't need to return anything
    }

    @RequestMapping(path= "/landmark/{id}/delete", method= RequestMethod.DELETE)
    public boolean deleteLandmark(@Valid @PathVariable("id") int landmarkId){
        boolean success = landmarkDao.deleteLandmark(landmarkId);
        if(!success){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Itinerary to be added was not found", null);
        }else{
            return success;
        }

    }
}
