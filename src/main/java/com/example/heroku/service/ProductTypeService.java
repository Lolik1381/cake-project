package com.example.heroku.service;

import com.example.heroku.model.ProductType;

import java.util.List;

public interface ProductTypeService {

    List<ProductType> findAll();
    ProductType findByName(String name);
    ProductType create(ProductType productType);
}