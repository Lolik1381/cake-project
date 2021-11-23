package com.example.heroku.repository;

import com.example.heroku.entity.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<CityEntity, String> {

    CityEntity findByShortDescription(String shortDescription);
}