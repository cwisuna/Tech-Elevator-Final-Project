package com.techelevator.dao;

import com.techelevator.model.Type;

import java.util.List;

public interface TypeDao {
     List<Type> listTypes();

     Type getType(int id);
}
