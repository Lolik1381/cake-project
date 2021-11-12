package com.example.heroku.service.impl;

import com.example.heroku.entity.FileEntity;
import com.example.heroku.repository.FileRepository;
import com.example.heroku.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;

    @Override
    public byte[] getFile(String id) {
        FileEntity file = fileRepository.findByIdOrElseNull(id);

        return file.getImage();
    }

    @Override
    public void upload(MultipartFile file) throws IOException {
        FileEntity fileEntity = FileEntity.builder()
                .id(UUID.randomUUID().toString())
                .image(file.getBytes())
                .build();

        fileRepository.save(fileEntity);
    }
}