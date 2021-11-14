package com.example.heroku.repository;

import com.example.heroku.entity.ProductTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductTypeRepository extends JpaRepository<ProductTypeEntity, String> {

    ProductTypeEntity findByName(String name);
}