package com.example.heroku.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.heroku.entity.CompanyEntity;

public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {
}