package com.techelevator.controller;

import com.techelevator.dao.TypeDao;
import com.techelevator.model.Type;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
public class TypeController {

    private TypeDao typeDao;

    public TypeController(TypeDao typeDao) {
        this.typeDao = typeDao;
    }

    @RequestMapping(path="/type/list", method= RequestMethod.GET)
    public List<Type> getAllTypes(){
        return typeDao.listTypes();
    }

    @RequestMapping(path="/type/{id}", method= RequestMethod.GET)
    public Type getType(@Valid @PathVariable("id") int typeId){

        Type result = typeDao.getType(typeId);

        if(result == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Type not found", null);
        }
        return result;
    }

}
