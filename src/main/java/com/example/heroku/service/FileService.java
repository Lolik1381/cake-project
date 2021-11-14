package com.example.heroku.service;

import com.example.heroku.model.File;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {

    byte[] getFile(String id);
    File upload(MultipartFile file) throws IOException;
}