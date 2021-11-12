package com.example.heroku.mapper;

import com.example.heroku.entity.ProductEntity;
import com.example.heroku.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toDto(ProductEntity source);
}