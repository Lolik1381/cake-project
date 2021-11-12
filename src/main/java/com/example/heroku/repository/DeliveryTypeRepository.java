package com.example.heroku.repository;

import com.example.heroku.entity.DeliveryTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryTypeRepository extends JpaRepository<DeliveryTypeEntity, String> {
}