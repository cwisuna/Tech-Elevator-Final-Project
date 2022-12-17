package com.techelevator.dao;

import com.techelevator.model.Hotel;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JdbcHotelDaoTest extends BaseDaoTests{

    private JdbcHotelDao sut;

    @BeforeEach
    void setUp() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcHotelDao(jdbcTemplate);
    }

    @Test
    void testListHotels() {

        List<Hotel> hotels = sut.listHotels();

        Assert.assertEquals(true, hotels);
    }

    @Test
    void testGetHotel() {

        Hotel hotel = sut.getHotel(1);

        Assert.assertEquals(1, hotel);
    }
}