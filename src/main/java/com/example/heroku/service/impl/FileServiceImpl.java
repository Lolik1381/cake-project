package com.example.heroku.service.impl;

import com.example.heroku.entity.FileEntity;
import com.example.heroku.mapper.FileMapper;
import com.example.heroku.model.File;
import com.example.heroku.repository.FileRepository;
import com.example.heroku.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;
    private final FileMapper fileMapper;

    @Override
    @Transactional(readOnly = true)
    public byte[] getFile(String id) {
        FileEntity file = fileRepository.findByIdOrElseNull(id);

        return file.getImage();
    }

    @Override
    @Transactional
    public File upload(MultipartFile file) throws IOException {
        return fileMapper.toDto(fileRepository.save(fileMapper.newEntity(file)));
    }
}