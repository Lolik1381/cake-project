package com.example.heroku.mapper;

import com.example.heroku.entity.CityEntity;
import com.example.heroku.model.City;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CityMapper {

    City toDto(CityEntity source);
}