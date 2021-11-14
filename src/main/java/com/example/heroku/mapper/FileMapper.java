package com.example.heroku.mapper;

import com.example.heroku.entity.FileEntity;
import com.example.heroku.model.File;
import org.mapstruct.Mapper;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface FileMapper {

    File toDto(FileEntity source);

    default FileEntity newEntity(MultipartFile file) throws IOException {
        return FileEntity.builder()
                .id(UUID.randomUUID().toString())
                .image(file.getBytes())
                .build();
    }
}