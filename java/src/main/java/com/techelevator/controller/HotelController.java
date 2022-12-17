package com.techelevator.controller;

import com.techelevator.dao.HotelDao;
import com.techelevator.model.Hotel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
public class HotelController {

    private HotelDao hotelDao;

    public HotelController(HotelDao hotelDao) {
        this.hotelDao = hotelDao;
    }

    @RequestMapping(path= "/hotel/list", method= RequestMethod.GET)
    public List<Hotel> listHotels(){
        return hotelDao.listHotels();
    }

    @RequestMapping(path= "/hotel/{id}", method= RequestMethod.GET)
    public Hotel getHotel(@Valid @PathVariable("id") int hotelId){
        Hotel result = hotelDao.getHotel(hotelId);
        if(result == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Hotel not found", null);
        }
        return result;
    }

}
