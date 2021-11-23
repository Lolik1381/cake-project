package com.example.heroku.mapper;

import com.example.heroku.entity.CityEntity;
import com.example.heroku.entity.DeliveryTypeEntity;
import com.example.heroku.entity.UserDataEntity;
import com.example.heroku.model.UserData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserDataMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "city", source = "city")
    @Mapping(target = "name", source = "source.name")
    @Mapping(target = "deliveryType", source = "deliveryType")
    UserDataEntity newEntity(UserData source, CityEntity city, DeliveryTypeEntity deliveryType, String id);

    UserData toDto(UserDataEntity source);
}