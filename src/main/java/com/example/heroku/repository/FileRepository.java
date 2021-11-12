package com.example.heroku.repository;

import com.example.heroku.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityNotFoundException;

public interface FileRepository extends JpaRepository<FileEntity, String> {

    default FileEntity findByIdOrElseNull(String id) {
        return findById(id).orElse(null);
    }
}