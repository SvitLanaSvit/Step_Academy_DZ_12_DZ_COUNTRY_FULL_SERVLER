package com.example.dz_12_country.entity;

import lombok.Setter;

@Setter
public class CityDB {
    private int id;
    private int country_id;
    private String name_city;
    private boolean is_capital;
    private String information;

    public int getId() {
        return id;
    }

    public int getCountry_id() {
        return country_id;
    }

    public String getName_city() {
        return name_city;
    }

    public boolean getCapital() {
        return is_capital;
    }

    public String getInformation() {
        return information;
    }
}
