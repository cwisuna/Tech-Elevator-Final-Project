package com.techelevator.controller;

import com.techelevator.dao.AddressDao;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class AddressController {

    private AddressDao addressDao;

    public AddressController(AddressDao addressDao) {
        this.addressDao = addressDao;
    }


}
