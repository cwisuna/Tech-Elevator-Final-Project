package com.techelevator.dao;

import com.techelevator.model.Hotel;

import java.util.List;

public interface HotelDao {

    List<Hotel> listHotels();

    Hotel getHotel(int hotelId);

}
