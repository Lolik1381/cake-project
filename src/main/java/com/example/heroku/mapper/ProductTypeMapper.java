package com.example.heroku.mapper;

import com.example.heroku.entity.ProductTypeEntity;
import com.example.heroku.model.ProductType;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface ProductTypeMapper {

    ProductType toDto(ProductTypeEntity source);

    default ProductTypeEntity newEntity(ProductType source) {
        return ProductTypeEntity.builder()
                .id(UUID.randomUUID().toString())
                .name(source.getName())
                .build();
    }
}