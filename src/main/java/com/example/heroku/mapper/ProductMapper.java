package com.example.heroku.mapper;

import com.example.heroku.entity.FileEntity;
import com.example.heroku.entity.ProductEntity;
import com.example.heroku.entity.ProductTypeEntity;
import com.example.heroku.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toDto(ProductEntity source);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "file", source = "file")
    @Mapping(target = "name", source = "source.name")
    ProductEntity newEntity(Product source, String id, ProductTypeEntity productType, FileEntity file);
}