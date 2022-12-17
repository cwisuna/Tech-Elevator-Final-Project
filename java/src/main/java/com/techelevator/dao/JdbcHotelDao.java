package com.techelevator.dao;

import com.techelevator.model.Address;
import com.techelevator.model.Hotel;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcHotelDao implements HotelDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcHotelDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Hotel> listHotels(){
        List<Hotel> list = new ArrayList<>();

        String sql = " SELECT id, address_id, name " +
                     " FROM hotels ";

        SqlRowSet result = jdbcTemplate.queryForRowSet(sql);

        while(result.next()){
            Hotel hotel = mapRowToHotel(result);
            String addressSql = " SELECT id, street, city, state, zip " +
                                " FROM addresses " +
                                " WHERE id = ?";
            SqlRowSet addressResult = jdbcTemplate.queryForRowSet(addressSql, hotel.getAddressId());

            if (addressResult.next()){
                hotel.setAddress(mapRowToAddress(addressResult));
            }
            list.add(hotel);
        }

        return list;
    }

    @Override
    public Hotel getHotel(int id){
        return listHotels().get(id-1);
    }

    private Hotel mapRowToHotel(SqlRowSet results){
        Hotel hotel = new Hotel();

        hotel.setHotelId(results.getInt("id"));
        hotel.setAddressId(results.getInt("address_id"));
        hotel.setName(results.getString("name"));

        return hotel;
    }
    private Address mapRowToAddress(SqlRowSet results){
        Address address = new Address();
        address.setAddressId(results.getInt("id"));
        address.setStreet(results.getString("street"));
        address.setCity(results.getString("city"));
        address.setStreet(results.getString("street"));
        address.setStateAbbrev(results.getString("state"));
        address.setZipCode(results.getInt("zip"));
        return address;
    }


}
