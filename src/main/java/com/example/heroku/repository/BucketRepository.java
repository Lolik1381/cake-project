package com.example.heroku.repository;

import com.example.heroku.entity.BucketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BucketRepository extends JpaRepository<BucketEntity, String> {
}