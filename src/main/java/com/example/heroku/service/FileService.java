package com.example.heroku.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {

    byte[] getFile(String id);
    void upload(MultipartFile file) throws IOException;
}