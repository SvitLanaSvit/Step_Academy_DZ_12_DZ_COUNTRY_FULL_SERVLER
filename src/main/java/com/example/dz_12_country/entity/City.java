package com.example.dz_12_country.entity;

import lombok.Setter;

@Setter
public class City {
    private int id;
    private String name_country;
    private String name_city;
    private boolean is_capital;
    private String information;

    public boolean getCapital(){
        return is_capital;
    }

    public int getId() {
        return id;
    }

    public String getName_country() {
        return name_country;
    }

    public String getName_city() {
        return name_city;
    }

    public String getInformation() {
        return information;
    }
}
