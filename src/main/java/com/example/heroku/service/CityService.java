package com.example.heroku.service;

import com.example.heroku.model.City;

import java.util.List;

public interface CityService {

    List<City> findAll();
}