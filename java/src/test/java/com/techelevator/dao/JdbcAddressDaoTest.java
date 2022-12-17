package com.techelevator.dao;

import com.techelevator.model.Address;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

class JdbcAddressDaoTest extends BaseDaoTests{
    protected static final Address ADDRESS_1 = new Address(1, "2400 3rd Ave", "Minneapolis", "MN", 55404);
    protected static final Address ADDRESS_2 = new Address(2,"60 E Broadway", "Bloomington", "MN", 55425);

    private JdbcAddressDao sut;


    @Before
    public void setUp() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcAddressDao(jdbcTemplate);
    }


    @Test
    public void testListOfAddresses() {

        List<Address> expectedAddresses = new ArrayList<>();
        expectedAddresses.add(ADDRESS_1);
        expectedAddresses.add(ADDRESS_2);
        List<Address> addresses = sut.listOfAddresses();

        Assert.assertEquals(expectedAddresses, addresses);

    }

    @Test
    void testGetAddress() {

        Address address = sut.getAddress(1);

        Assert.assertEquals(1, address);

    }

    @Test
    void testCreateAddress() {

        Address address = new Address(0,"2400 4th Ave", "Bloomington", "MN", 55403);

        int addressId = sut.createAddress(address);

        Assert.assertEquals(0, addressId);

    }

}