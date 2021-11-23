package com.example.heroku.service;

import com.example.heroku.model.Product;
import com.example.heroku.model.ResponsePage;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    ResponsePage<Product> getAllProducts(String searchText, List<String> productTypes, Pageable pageable);
    Product findById(String id);
    Product create(Product product);
}