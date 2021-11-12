package com.example.heroku.repository;

import com.example.heroku.entity.UserDateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDateRepository extends JpaRepository<UserDateEntity, String> {
}