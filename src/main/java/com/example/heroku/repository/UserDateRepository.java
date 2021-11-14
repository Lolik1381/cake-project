package com.example.heroku.repository;

import com.example.heroku.entity.UserDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDateRepository extends JpaRepository<UserDataEntity, String> {
}