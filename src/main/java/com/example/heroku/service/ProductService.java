package com.example.heroku.service;

import com.example.heroku.model.Product;
import com.example.heroku.model.ResponsePage;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    ResponsePage<Product> getAllProducts(String searchText, Pageable pageable);
    Product findById(String id);
}