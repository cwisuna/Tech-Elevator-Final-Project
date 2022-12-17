package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Type {

    @JsonProperty("id")
    private int typeId;
    @JsonProperty("name")
    private String name;

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
