package com.techelevator.dao;

import com.techelevator.model.Address;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcAddressDao implements AddressDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcAddressDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Address> listOfAddresses(){
        List<Address> list = new ArrayList<>();

        String sql = " SELECT id, street, city, state, zip" +
                " FROM addresses ";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
        while(result.next()){
            list.add(mapRowToAddress(result));
        }

        return list;
    }

    @Override
    public Address getAddress(int id){
        if(id == 0) throw new IllegalArgumentException("Address ID cannot be null.");

        String sql = " SELECT id, street, city, state, zip " +
                     " FROM addresses " +
                     " WHERE id = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);

        if (result.next()){
            return mapRowToAddress(result);
        }
        return null;
    }

    @Override
    public int createAddress(Address address){
        String sql = "INSERT INTO addresses (street, city, state, zip) " +
                " VALUES(?, ?, ?, ?) RETURNING id; ";

        int addressId = jdbcTemplate.queryForObject(sql, int.class, address.getStreet(),
                                                                    address.getCity(),
                                                                    address.getStateAbbrev(),
                                                                    address.getZipCode());

        return addressId;
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
