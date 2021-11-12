package com.example.heroku.repository;

import com.example.heroku.entity.DeliveryIntervalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryIntervalRepository extends JpaRepository<DeliveryIntervalEntity, String> {
}